package jaro2gw.klkr.model.clicker

import android.support.constraint.ConstraintLayout
import android.view.View
import android.widget.TextView
import jaro2gw.klkr.MainActivity
import kotlinx.android.synthetic.main.clicker.view.*

class Clicker(private val mainActivity: MainActivity) {
    var name: String = "Clicker"
    var count: Int = 0
    var color: Int = android.R.color.holo_orange_light

    private lateinit var countView: TextView
    private lateinit var nameView: TextView
    private lateinit var clickerLayout: ConstraintLayout

    fun update(position: Int, clickerView: View) {
        with(clickerView) {
            countView = textView_count
            nameView = textView_name
            clickerLayout = conLayout_clicker
            btn_dec.setOnClickListener { mainActivity.update(position, -1) }
            btn_inc.setOnClickListener { mainActivity.update(position, +1) }
            imgBtn_edit.setOnClickListener { mainActivity.promptEdit(position) }
            imgBtn_reset.setOnClickListener { mainActivity.promptConfirm(position, "RESET") }
            imgBtn_delete.setOnClickListener { mainActivity.promptConfirm(position, "DELETE") }
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