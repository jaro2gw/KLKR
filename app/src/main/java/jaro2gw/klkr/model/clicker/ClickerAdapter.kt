package jaro2gw.klkr.model.clicker

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import jaro2gw.klkr.R

class ClickerAdapter(
        context: Context,
        val list: List<Clicker>
) : ArrayAdapter<Clicker>(context, R.layout.clicker, list) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val clicker = inflater.inflate(R.layout.clicker, parent, false)

        val btnDec = clicker.findViewById(R.id.button_dec) as Button
        val btnInc = clicker.findViewById(R.id.button_inc) as Button

        val klkr = list.get(position)

        val countView = clicker.findViewById(R.id.count_view) as TextView
        countView.setSelectAllOnFocus(true)
        countView.text = klkr.count.toString()

        val nameView = clicker.findViewById(R.id.name_view) as TextView
        nameView.setSelectAllOnFocus(true)
        nameView.text = klkr.name

        btnDec.setOnClickListener { updateQty(countView, list.get(position), -1) }
        btnInc.setOnClickListener { updateQty(countView, list.get(position), 1) }

        return clicker
    }

    private fun updateQty(countView: TextView, element: Clicker, i: Int) {
        element.count += i
        countView.text = element.count.toString()
    }
}