package jaro2gw.klkr

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.LinearLayout
import android.widget.ListView
import jaro2gw.klkr.model.clicker.Clicker
import java.util.*

class MainActivity : AppCompatActivity() {
    val clickerList = LinkedList<Clicker>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        clickerList.clear()

        val clickers: ListView = findViewById(R.id.clickers)

        val empty: LinearLayout = findViewById(R.id.empty)

        clickers.visibility = if (clickerList.isEmpty()) View.GONE else View.VISIBLE
        empty.visibility = if (clickerList.isEmpty()) View.VISIBLE else View.GONE

    }

    fun createClicker(view: View) {
        println("createClicker function was triggered")
//        val intent = Intent(this, Clicker::class.java).apply {}
//        clickerList.add(Clicker())
    }
}
