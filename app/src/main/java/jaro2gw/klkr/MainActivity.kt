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
import jaro2gw.klkr.model.dialog.MyDialogFragment
import java.util.*

class MainActivity : AppCompatActivity() {
    val clickerList = LinkedList<Clicker>()
    lateinit var adapter: ClickerAdapter
    lateinit var controller: ClickerController

    override fun onPause() {
        System.err.println("PAUSING")
        super.onPause()
        //TODO save clicker list state
    }

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


    fun prompt(position: Int, action: String) {
        System.err.println("PROMPTING USER FOR CONFIRMATION...")
        val dialog = MyDialogFragment()

        dialog.arguments = Bundle()
        with(dialog.arguments!!) {
            putInt("position", position)
            putString("action", action)
            putString("message", when (action) {
                "RESET"  -> resources.getString(R.string.msg_reset)
                "DELETE" -> resources.getString(R.string.msg_delete)
                else     -> ""
            })
        }

        dialog.show(supportFragmentManager, "PROMPT")
        System.err.println("DIALOG SHOULD SHOW")
    }
}
