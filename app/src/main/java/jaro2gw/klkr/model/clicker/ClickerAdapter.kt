package jaro2gw.klkr.model.clicker

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import jaro2gw.klkr.R

class ClickerAdapter(
        context: Context,
        val list: ArrayList<Clicker>
) : ArrayAdapter<Clicker>(context, R.layout.clicker, list) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val clicker = inflater.inflate(R.layout.clicker, parent, false)

//        val btnDec = clicker.findViewById(R.id.button_dec) as Button
//        val btnInc = clicker.findViewById(R.id.button_inc) as Button

        return clicker
    }
}