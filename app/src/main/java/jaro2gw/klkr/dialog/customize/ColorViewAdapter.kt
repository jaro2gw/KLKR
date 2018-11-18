package jaro2gw.klkr.dialog.customize

import android.content.Context
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import jaro2gw.klkr.R
import jaro2gw.klkr.database.Clicker
import kotlinx.android.synthetic.main.activity_customize.*

class ColorViewAdapter(private val context: CustomizeActivity) : ArrayAdapter<Int>(context, R.layout.color_choice, colors) {
    companion object {
        val colors = arrayOf(
                android.R.color.holo_purple,
                android.R.color.holo_blue_light,
                android.R.color.holo_blue_dark,
                android.R.color.holo_green_light,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_light,
                android.R.color.holo_orange_dark,
                android.R.color.holo_red_light,
                android.R.color.holo_red_dark)

    }

    private val clicker: Clicker = context.clicker

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View = if (convertView == null) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        with(inflater.inflate(R.layout.color_choice, parent, false)) {
            setBackgroundResource(if (colors[position] == clicker.color) R.drawable.ic_check_box_black_24dp else R.drawable.ic_check_box_black_solid_24dp)
            backgroundTintList = ContextCompat.getColorStateList(context, colors[position])
            setOnClickListener {
                Log.d("COLOR VIEW", "${colors[position].asColorString()} COLOR CLICKED")
                this@ColorViewAdapter.context.gridView_color.getChildAt(colors.indexOf(clicker.color)).setBackgroundResource(R.drawable.ic_check_box_black_solid_24dp)
                clicker.color = colors[position]
                setBackgroundResource(R.drawable.ic_check_box_black_24dp)
            }
            return@with this
        }
    } else convertView
}

private fun Int.asColorString(): String = when (this) {
    android.R.color.holo_purple       -> "PURPLE"
    android.R.color.holo_blue_light   -> "LIGHT BLUE"
    android.R.color.holo_blue_dark    -> "DARK BLUE"
    android.R.color.holo_green_light  -> "LIGHT GREEN"
    android.R.color.holo_green_dark   -> "DARK GREEN"
    android.R.color.holo_orange_light -> "LIGHT ORANGE"
    android.R.color.holo_orange_dark  -> "DARK ORANGE"
    android.R.color.holo_red_light    -> "LIGHT RED"
    android.R.color.holo_red_dark     -> "DARK RED"
    else                              -> "UNKNOWN"
}
