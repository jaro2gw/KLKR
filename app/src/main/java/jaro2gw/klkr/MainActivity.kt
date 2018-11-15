package jaro2gw.klkr

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import jaro2gw.klkr.database.Clicker
import jaro2gw.klkr.database.ClickerAdapter
import jaro2gw.klkr.database.ClickerVM
import jaro2gw.klkr.dialog.confirm.ConfirmDialog
import jaro2gw.klkr.dialog.confirm.ConfirmListener
import jaro2gw.klkr.dialog.customize.CustomizeActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        const val CREATE_REQUEST_CODE = 0
        const val EDIT_REQUEST_CODE = 1
    }

    private lateinit var clickerVM: ClickerVM
    private lateinit var clickerAdapter: ClickerAdapter
    internal lateinit var confirmListener: ConfirmListener

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK)
            with(data!!) {
                val clicker = getParcelableExtra<Clicker>("clicker")
                when (requestCode) {
                    EDIT_REQUEST_CODE   -> clickerVM.update(clicker)
                    CREATE_REQUEST_CODE -> clickerVM.insert(clicker)
                    else                -> {
                    }
                }
            }
//        updateList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        clickerAdapter = ClickerAdapter(this)
        recyclerView_clickers.adapter = clickerAdapter
        recyclerView_clickers.layoutManager = LinearLayoutManager(this)
        recyclerView_clickers.addItemDecoration(ClickerAdapter.VerticalSpace())

        confirmListener = ConfirmListener(this)

        clickerVM = ViewModelProviders.of(this).get(ClickerVM::class.java)
        clickerVM.clickers.observe(this, Observer { clickers ->
            clickerAdapter.setClickers(clickers!!)
            updateList()
        })

        getPreferences(Context.MODE_PRIVATE).edit().remove("RESET").remove("DELETE").apply()

        imgBtn_addClicker.setOnClickListener {
            startActivityForResult(customizeIntent(Clicker()), CREATE_REQUEST_CODE)
        }
    }

    private fun updateList() {
        linLayout_empty.visibility = if (clickerAdapter.itemCount == 0) View.VISIBLE else View.GONE
    }

    private fun customizeIntent(clicker: Clicker) = Intent(this, CustomizeActivity::class.java).putExtra("clicker", clicker)
//                    .putExtra("name", clicker.name)
//                    .putExtra("count", clicker.count.toString())
//                    .putExtra("color", clicker.color)

    fun promptEdit(clicker: Clicker) = startActivityForResult(customizeIntent(clicker), EDIT_REQUEST_CODE)

    fun promptConfirm(clicker: Clicker, action: String) =
            if (!getPreferences(Context.MODE_PRIVATE).getBoolean(action, false))
                ConfirmDialog.get(clicker, action).show(supportFragmentManager, "CONFIRM")
            else confirmListener.performAction(clicker, action)

    fun updateCount(clicker: Clicker, x: Int) {
        clicker.count += x
        clickerVM.update(clicker)
    }

    fun reset(clicker: Clicker) = clickerVM.reset(clicker.id)

    fun delete(clicker: Clicker) = clickerVM.delete(clicker)
}
