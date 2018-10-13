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
    private var clickerList = LinkedList<Clicker>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

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
        clickers.adapter = adapter
    }

    fun checkForEmptyList() {
        findViewById<LinearLayout>(R.id.linLayout_empty).visibility = if (clickerList.isEmpty()) View.VISIBLE else View.GONE
    }
}
