package jaro2gw.klkr.dialog.edit

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import jaro2gw.klkr.R
import kotlinx.android.synthetic.main.activity_edit.*

class ColorViewAdapter(private val context: EditActivity) : ArrayAdapter<Int>(context, R.layout.color_choice, colors) {
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

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View = if (convertView == null) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        with(inflater.inflate(R.layout.color_choice, parent, false)) {
            backgroundTintList = ContextCompat.getColorStateList(context, colors[position])
            setOnClickListener {
                with(this@ColorViewAdapter.context) {
                    val color = intent.getIntExtra("color", 0)
                    gridView_color.getChildAt(colors.indexOf(color)).setBackgroundResource(R.drawable.ic_check_box_black_solid_24dp)
                    intent.putExtra("color", colors[position])
                }
                setBackgroundResource(R.drawable.ic_check_box_black_24dp)
                notifyDataSetChanged()
            }
            return@with this
        }
    } else convertView
}