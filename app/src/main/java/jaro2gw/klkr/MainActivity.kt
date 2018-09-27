package jaro2gw.klkr

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.ListView
import jaro2gw.klkr.model.clicker.Clicker
import jaro2gw.klkr.model.clicker.ClickerAdapter
import java.util.*

class MainActivity : AppCompatActivity() {
    private val clickerList = LinkedList<Clicker>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        clickerList.clear()
//        clickerList.add(Clicker("XD", 69))

        val adapter = ClickerAdapter(this, clickerList)

        val addClickerButton = findViewById<ImageButton>(R.id.imgBtn_addClicker)
        val clickers = findViewById<ListView>(R.id.listView_clickers)
        val empty = findViewById<LinearLayout>(R.id.linLayout_empty)

        addClickerButton.setOnClickListener {
            if (clickerList.isEmpty()) empty.visibility = View.GONE
            clickerList.add(Clicker())
            adapter.notifyDataSetChanged()
        }

        clickers.adapter = adapter
    }
}
