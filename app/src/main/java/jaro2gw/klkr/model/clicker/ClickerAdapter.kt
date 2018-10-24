package jaro2gw.klkr.model.clicker

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import jaro2gw.klkr.MainActivity
import jaro2gw.klkr.R

class ClickerAdapter(val context: MainActivity, val list: MutableList<Clicker> = context.clickerList) : ArrayAdapter<Clicker>(context, R.layout.clicker, list) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//        val clickerView = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE).inflate(R.layout.clicker, parent, false) as View
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val clickerView = inflater.inflate(R.layout.clicker, parent, false) as View

        list[position].update(position, clickerView)

        return clickerView
    }
}