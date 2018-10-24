package jaro2gw.klkr

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.ListView
import jaro2gw.klkr.model.clicker.Clicker
import jaro2gw.klkr.model.clicker.ClickerAdapter
import jaro2gw.klkr.model.clicker.ClickerController
import java.util.*

class MainActivity : AppCompatActivity() {
    val clickerList = LinkedList<Clicker>()
    lateinit var adapter: ClickerAdapter
    lateinit var controller: ClickerController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = ClickerAdapter(this)
        controller = ClickerController(this)

        setContentView(R.layout.activity_main)

//        val addClickerButton = findViewById<ImageButton>(R.id.imgBtn_addClicker)
        findViewById<ImageButton>(R.id.imgBtn_addClicker).setOnClickListener {
            clickerList.add(Clicker(controller))
            updateList()
        }

        updateList()
        findViewById<ListView>(R.id.listView_clickers).adapter = adapter
    }

    fun updateList() {
        adapter.notifyDataSetChanged()
        findViewById<LinearLayout>(R.id.linLayout_empty).visibility = if (clickerList.isEmpty()) View.VISIBLE else View.GONE
    }
}
