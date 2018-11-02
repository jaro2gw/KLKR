package jaro2gw.klkr.dialog.edit

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import jaro2gw.klkr.MainActivity
import jaro2gw.klkr.R

class ColorViewAdapter(private val context: MainActivity, val colors: IntArray = ColorViewAdapter.colors) : ArrayAdapter<Int>(context, R.layout.item_color, colors.toList()) {
    companion object {
        val colors: IntArray = intArrayOf(
                android.R.color.holo_purple,
                android.R.color.holo_blue_light,
                android.R.color.holo_blue_dark,
                android.R.color.holo_green_light,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_light,
                android.R.color.holo_orange_dark,
                android.R.color.holo_red_light,
                android.R.color.holo_red_dark,
                android.R.color.white)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View = if (convertView == null) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        with(inflater.inflate(R.layout.item_color, parent, false)) {
            setBackgroundResource(colors[position])
            return@with this
        }
    } else convertView as ImageView
}