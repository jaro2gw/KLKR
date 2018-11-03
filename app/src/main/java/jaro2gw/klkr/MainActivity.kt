package jaro2gw.klkr

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.ListView
import jaro2gw.klkr.dialog.confirm.ConfirmController
import jaro2gw.klkr.model.clicker.Clicker
import jaro2gw.klkr.model.clicker.ClickerAdapter
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: ClickerAdapter
    private lateinit var empty: LinearLayout
    private lateinit var listView: ListView
    private lateinit var controller: MainController
    var clickerList = LinkedList<Clicker>()

    fun getController(): MainController = controller

    override fun onPause() {
        System.err.println("PAUSING")
        super.onPause()
        //TODO save clicker list state
    }

    fun edit(position: Int, name: String, count: Int, color: Int) {
        clickerList[position].edit(name, count, color)
        updateList()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK)
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
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = ClickerAdapter(this)
        controller = MainController(this)
        controller.confirmController = ConfirmController(this)

        findViewById<ImageButton>(R.id.imgBtn_addClicker).setOnClickListener {
            clickerList.add(Clicker(controller))
            updateList()
        }

        empty = findViewById(R.id.linLayout_empty)

        listView = findViewById(R.id.listView_clickers)
        listView.adapter = adapter

        updateList()
    }

    fun updateList() {
        adapter.notifyDataSetChanged()
        empty.visibility = if (clickerList.isEmpty()) View.VISIBLE else View.GONE
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        System.err.println()
        super.onSaveInstanceState(outState)
        //TODO sth
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        System.err.println()
        super.onRestoreInstanceState(savedInstanceState)
        //TODO sth
    }
}
