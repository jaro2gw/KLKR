package jaro2gw.klkr.model.clicker

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import jaro2gw.klkr.MainActivity
import jaro2gw.klkr.R

class ClickerAdapter(private val context: MainActivity, private val list: MutableList<Clicker> = context.getClickers()) : ArrayAdapter<Clicker>(context, R.layout.clicker, list) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val clickerView = if (convertView == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            inflater.inflate(R.layout.clicker, parent, false)
        } else convertView

        list[position].update(position, clickerView)

        return clickerView
    }
}