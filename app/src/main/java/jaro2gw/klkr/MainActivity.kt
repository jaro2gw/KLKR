package jaro2gw.klkr

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import jaro2gw.klkr.dialog.confirm.ConfirmDialog
import jaro2gw.klkr.dialog.confirm.ConfirmListener
import jaro2gw.klkr.dialog.edit.EditActivity
import jaro2gw.klkr.model.clicker.Clicker
import jaro2gw.klkr.model.clicker.ClickerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    companion object {
        const val EDIT_ACTIVITY_REQUEST_CODE = 1
    }

    private lateinit var adapter: ClickerAdapter
    private lateinit var confirmListener: ConfirmListener
    private var clickerList = LinkedList<Clicker>()

    fun getClickers(): LinkedList<Clicker> = clickerList

    fun getConfirmListener(): ConfirmListener = confirmListener

    override fun onPause() {
        Log.d("MAIN ACTIVITY", "PAUSING")
        super.onPause()
        //TODO save clicker list state
    }

    fun edit(position: Int, name: String, count: Int, color: Int) = clickerList[position].edit(name, count, color)

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == EDIT_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK)
            with(data!!) {
                edit(
                        getIntExtra("position", -1),
                        getStringExtra("name"),
                        with(getStringExtra("count")) {
                            when (this) {
                                "", null -> 0
                                else     -> this.toInt()
                            }
                        },
                        getIntExtra("color", -1)
                )
            }
        adapter.notifyDataSetChanged()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = ClickerAdapter(this)
        confirmListener = ConfirmListener(this)

        imgBtn_addClicker.setOnClickListener {
            clickerList.add(Clicker(this))
            updateList()
        }

        listView_clickers.adapter = adapter

        getPreferences(Context.MODE_PRIVATE).edit().remove("RESET").remove("DELETE").apply()

        linLayout_empty.visibility = if (clickerList.isEmpty()) View.VISIBLE else View.GONE
    }

    fun updateList() {
        adapter.notifyDataSetChanged()
        linLayout_empty.visibility = if (clickerList.isEmpty()) View.VISIBLE else View.GONE
    }

    fun update(position: Int, x: Int) {
        with(clickerList[position]) {
            count += x
            updateViews()
        }
        adapter.notifyDataSetChanged()
    }

    fun promptEdit(position: Int) = with(clickerList[position]) {
        startActivityForResult(Intent(this@MainActivity, EditActivity::class.java)
                .putExtra("position", position)
                .putExtra("name", name)
                .putExtra("count", count.toString())
                .putExtra("color", color),
                MainActivity.EDIT_ACTIVITY_REQUEST_CODE)
    }

    fun promptConfirm(position: Int, action: String) = if (!getPreferences(Context.MODE_PRIVATE).getBoolean(action, false)) {
        ConfirmDialog.new(position, action).show(supportFragmentManager, "CONFIRM")
    } else confirmListener.performAction(position, action)
}
