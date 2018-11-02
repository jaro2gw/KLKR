package jaro2gw.klkr.model.clicker

import android.support.constraint.ConstraintLayout
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import jaro2gw.klkr.MainController
import jaro2gw.klkr.R

class Clicker(private val controller: MainController) {
    var name: String = "Clicker"
    var count: Int = 0
    var color: Int = android.R.color.holo_orange_light

    private lateinit var countView: TextView
    private lateinit var nameView: TextView
    private lateinit var clickerLayout: ConstraintLayout
    private var position: Int = 0

    fun update(position: Int, clickerView: View) {
        this.position = position
        with(clickerView) {
            countView = findViewById(R.id.textView_count)
            nameView = findViewById(R.id.textView_name)
            clickerLayout = findViewById(R.id.conLayout_clicker)
            findViewById<Button>(R.id.btn_dec).setOnClickListener { updateCount(-1) }
            findViewById<Button>(R.id.btn_inc).setOnClickListener { updateCount(+1) }
            findViewById<ImageButton>(R.id.imgBtn_edit).setOnClickListener { controller.promptEdit(this@Clicker.position) }
            findViewById<ImageButton>(R.id.imgBtn_reset).setOnClickListener { controller.promptConfirmation(this@Clicker.position, "RESET") }
            findViewById<ImageButton>(R.id.imgBtn_delete).setOnClickListener { controller.promptConfirmation(this@Clicker.position, "DELETE") }
        }
        updateAll()
    }

    fun edit(name: String, count: Int, color: Int) {
        this.name = name
        this.count = count
        this.color = color
        updateAll()
    }

    fun reset() {
        count = 0
        updateCountView()
    }

    private fun updateCount(x: Int) {
        count += x
        updateCountView()
    }

    private fun updateAll() {
        nameView.text = name
        clickerLayout.setBackgroundResource(color)
        updateCountView()
    }

    private fun updateCountView() {
        countView.text = count.toString()
    }
}