package jaro2gw.klkr

import android.annotation.SuppressLint
import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import jaro2gw.klkr.database.Clicker
import jaro2gw.klkr.database.ClickerAdapter
import jaro2gw.klkr.database.ClickerVM
import jaro2gw.klkr.dialog.confirm.ConfirmDialog
import jaro2gw.klkr.dialog.confirm.ConfirmListener
import jaro2gw.klkr.dialog.customize.CustomizeActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    enum class Action { CREATE, EDIT, RESET, DELETE }
    companion object {
        const val CREATE_REQUEST_CODE = 0
        const val EDIT_REQUEST_CODE = 1
    }

    private lateinit var toastMap: Map<Action, Toast>
    private lateinit var clickerVM: ClickerVM
    private lateinit var clickerAdapter: ClickerAdapter
    internal lateinit var confirmListener: ConfirmListener

    override fun onPause() {
        super.onPause()
        //TODO write database to file
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK)
            with(data!!.getParcelableExtra<Clicker>("clicker")) {
                when (requestCode) {
                    CREATE_REQUEST_CODE -> {
                        clickerVM.insert(this)
                        makeToast(Action.CREATE)
                    }
                    EDIT_REQUEST_CODE   -> {
                        clickerVM.update(this)
                        makeToast(Action.EDIT)
                    }
                }
            }
    }

    @SuppressLint("ShowToast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toastMap = mapOf(
                Action.CREATE to "Clicker created!",
                Action.EDIT to "Clicker edited!",
                Action.RESET to "Clicker reset!",
                Action.DELETE to "Clicker deleted!")
                .mapValues { entry -> Toast.makeText(this, entry.value, Toast.LENGTH_SHORT) }

        clickerAdapter = ClickerAdapter(this)
        recyclerView_clickers.adapter = clickerAdapter
        recyclerView_clickers.layoutManager = LinearLayoutManager(this)
        recyclerView_clickers.addItemDecoration(ClickerAdapter.VerticalSpace())

        confirmListener = ConfirmListener(this)

        clickerVM = ViewModelProviders.of(this).get(ClickerVM::class.java)
        clickerVM.clickers.observe(this, Observer { clickers ->
            clickerAdapter.setClickers(clickers!!)
            linLayout_empty.visibility = if (clickers.isNullOrEmpty()) View.VISIBLE else View.GONE
        })

        getPreferences(Context.MODE_PRIVATE).edit().remove(Action.RESET.name).remove(Action.DELETE.name).apply()

        imgBtn_addClicker.setOnClickListener {
            startActivityForResult(customizeIntent(Clicker()), CREATE_REQUEST_CODE)
        }
    }

    private fun customizeIntent(clicker: Clicker) = Intent(this, CustomizeActivity::class.java).putExtra("clicker", clicker)

    fun promptEdit(clicker: Clicker) = startActivityForResult(customizeIntent(clicker), EDIT_REQUEST_CODE)

    fun promptConfirm(clicker: Clicker, action: Action) =
            if (!getPreferences(Context.MODE_PRIVATE).getBoolean(action.name, false))
                ConfirmDialog.get(clicker, action).show(supportFragmentManager, "CONFIRM")
            else confirmListener.performAction(clicker, action)

    fun updateCount(clicker: Clicker, x: Int) {
        clicker.count += x
        clickerVM.update(clicker)
    }

    fun reset(clicker: Clicker) {
        clickerVM.reset(clicker.id)
        makeToast(Action.RESET)
    }

    fun delete(clicker: Clicker) {
        clickerVM.delete(clicker)
        makeToast(Action.DELETE)
    }

    private fun makeToast(action: Action) = toastMap.forEach { a, t -> if (a == action) t.show() else t.cancel() }
}
