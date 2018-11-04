package jaro2gw.klkr.model.clicker

import android.support.constraint.ConstraintLayout
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import jaro2gw.klkr.MainActivity
import jaro2gw.klkr.R

class Clicker(private val mainActivity: MainActivity) {
    var name: String = "Clicker"
    var count: Int = 0
    var color: Int = android.R.color.holo_orange_light

    private lateinit var countView: TextView
    private lateinit var nameView: TextView
    private lateinit var clickerLayout: ConstraintLayout

    fun update(position: Int, clickerView: View) {
        with(clickerView) {
            countView = findViewById(R.id.textView_count)
            nameView = findViewById(R.id.textView_name)
            clickerLayout = findViewById(R.id.conLayout_clicker)
            findViewById<Button>(R.id.btn_dec).setOnClickListener { mainActivity.update(position, -1) }
            findViewById<Button>(R.id.btn_inc).setOnClickListener { mainActivity.update(position, +1) }
            findViewById<ImageButton>(R.id.imgBtn_edit).setOnClickListener { mainActivity.promptEdit(position) }
            findViewById<ImageButton>(R.id.imgBtn_reset).setOnClickListener { mainActivity.promptConfirm(position, "RESET") }
            findViewById<ImageButton>(R.id.imgBtn_delete).setOnClickListener { mainActivity.promptConfirm(position, "DELETE") }
        }
        updateViews()
    }

    fun edit(name: String, count: Int, color: Int) {
        this.name = name
        this.count = count
        this.color = color
        updateViews()
    }

    fun reset() {
        count = 0
        updateCountView()
    }

    fun updateViews() {
        nameView.text = name
        clickerLayout.setBackgroundResource(color)
        updateCountView()
    }

    private fun updateCountView() {
        countView.text = count.toString()
    }
}