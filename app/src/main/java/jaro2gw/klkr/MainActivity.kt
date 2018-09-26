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
    val clickerList = LinkedList<Clicker>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        clickerList.clear()
        clickerList.add(Clicker("XD", 69))

        val adapter = ClickerAdapter(this, clickerList)

        val add = findViewById<ImageButton>(R.id.addButton)
        add.setOnClickListener {
            clickerList.add(Clicker())
            adapter.notifyDataSetChanged()
        }

        val clickers = findViewById<ListView>(R.id.clickers)
        val empty = findViewById<LinearLayout>(R.id.empty)

        clickers.adapter = adapter

        clickers.visibility = if (clickerList.isEmpty()) View.GONE else View.VISIBLE
        empty.visibility = if (clickerList.isEmpty()) View.VISIBLE else View.GONE
    }
}
