package jaro2gw.klkr.dialog.edit

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.widget.EditText
import android.widget.GridView
import android.widget.ImageView
import jaro2gw.klkr.MainActivity
import jaro2gw.klkr.R

class EditController(val context: MainActivity) : EditDialog.EditListener {
    override fun colorSelect(gridView: GridView, adapter: ColorViewAdapter, arguments: Bundle, position: Int) {
        (gridView.getChildAt(adapter.colors.indexOf(arguments.getInt("color"))) as ImageView).setImageResource(R.drawable.blank)
        (gridView.getChildAt(position) as ImageView).setImageResource(R.drawable.check)
        arguments.putInt("color", adapter.colors[position])
        adapter.notifyDataSetChanged()
    }

    fun edit(position: Int, name: String, count: Int, color: Int) = with(context) {
//        with(clickerList[position]) {
//            this.name = name
//            this.count = count
//            this.color = color
//        }
        clickerList[position].edit(name, count, color)
        updateList()
    }

    override fun editClick(dialog: DialogFragment) = with(dialog.arguments!!) {
        edit(getInt("position"),
                dialog.dialog.findViewById<EditText>(R.id.editText_name).text.toString(),
                with(dialog.dialog.findViewById<EditText>(R.id.editText_count).text.toString()) {
                    when (this) {
                        ""   -> 0
                        else -> this.toInt()
                    }
                },
                getInt("color"))
    }
}