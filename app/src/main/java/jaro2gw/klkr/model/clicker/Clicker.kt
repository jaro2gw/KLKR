package jaro2gw.klkr.model.clicker

import android.support.constraint.ConstraintLayout
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import jaro2gw.klkr.R

class Clicker(private val controller: ClickerController) {
    private var name: String = "Clicker"
    private var count: Int = 0
    private var color: Int = R.color.colorAccent

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
            findViewById<ImageButton>(R.id.imgBtn_edit).setOnClickListener { controller.edit(this@Clicker.position) }
            findViewById<ImageButton>(R.id.imgBtn_reset).setOnClickListener { controller.prompt(this@Clicker.position, "RESET") }
            findViewById<ImageButton>(R.id.imgBtn_delete).setOnClickListener { controller.prompt(this@Clicker.position, "DELETE") }
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
        System.err.println("RESETTING COUNT VIEW...")
        count = 0
        updateCountView()
    }

    private fun updateCount(x: Int) {
        System.err.println("UPDATING COUNT VIEW...")
        count += x
        updateCountView()
    }

    private fun updateAll() {
        updateNameView()
        updateCountView()
        updateBackgroundColor()
    }

    private fun updateNameView() {
        nameView.text = name
    }

    private fun updateCountView() {
        countView.text = count.toString()
    }

    private fun updateBackgroundColor() = clickerLayout.setBackgroundResource(color)
}