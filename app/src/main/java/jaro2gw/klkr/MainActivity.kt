package jaro2gw.klkr

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.ListView
import jaro2gw.klkr.model.clicker.Clicker
import jaro2gw.klkr.model.clicker.ClickerAdapter
import jaro2gw.klkr.utils.GsonParser
import java.util.*

class MainActivity : AppCompatActivity() {
    private val clickerList = GsonParser.readClickers() as LinkedList<Clicker>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        clickerList.clear()
        clickerList.add(Clicker("XD", 69, R.color.colorAccentDark))

        val adapter = ClickerAdapter(this, clickerList)

        val addClickerButton = findViewById<ImageButton>(R.id.imgBtn_addClicker)
        val clickers = findViewById<ListView>(R.id.listView_clickers)

        addClickerButton.setOnClickListener {
            clickerList.add(Clicker())
            checkForEmptyList()
            adapter.notifyDataSetChanged()
        }

        checkForEmptyList()
        GsonParser.writeClickers(clickerList)
        clickers.adapter = adapter
    }

    override fun onDestroy() {
        GsonParser.writeClickers(clickerList)
        super.onDestroy()
    }

    fun checkForEmptyList() {
        findViewById<LinearLayout>(R.id.linLayout_empty).visibility = if (clickerList.isEmpty()) View.VISIBLE else View.GONE
    }
}
