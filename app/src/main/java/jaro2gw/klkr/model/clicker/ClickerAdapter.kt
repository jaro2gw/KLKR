package jaro2gw.klkr.model.clicker

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import jaro2gw.klkr.R

class ClickerAdapter(context: Context, val list: MutableList<Clicker>) : ArrayAdapter<Clicker>(context, R.layout.clicker, list) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val clickerView = inflater.inflate(R.layout.clicker, parent, false)
        val clicker = list[position]

        val countView = clickerView.findViewById<TextView>(R.id.textView_count)
        countView.text = clicker.count.toString()

        val nameView = clickerView.findViewById<TextView>(R.id.textView_name)
        nameView.text = clicker.name

        with(clickerView) {
            findViewById<Button>(R.id.btn_dec).setOnClickListener { updateCount(countView, clicker, -1) }
            findViewById<Button>(R.id.btn_inc).setOnClickListener { updateCount(countView, clicker, +1) }
            findViewById<ImageButton>(R.id.imgBtn_edit).setOnClickListener { editClickerValues(clicker, countView, nameView) }
            findViewById<ImageButton>(R.id.imgBtn_reset).setOnClickListener { resetClickerCount(countView, clicker) }
            findViewById<ImageButton>(R.id.imgBtn_delete).setOnClickListener { deleteClicker(position) }
        }

        return clickerView
    }

    private fun editClickerValues(clicker: Clicker, countView: TextView, nameView: TextView) {
        //TODO prompt for new values
    }

    private fun resetClickerCount(countView: TextView, clicker: Clicker) {
        //TODO prompt for confirmation
        clicker.count = 0
        countView.text = clicker.count.toString()
    }

    private fun deleteClicker(position: Int) {
        //TODO prompt for confirmation
        list.removeAt(position)
        notifyDataSetChanged()
    }

    private fun updateCount(countView: TextView, clicker: Clicker, i: Int) {
        clicker.count += i
        countView.text = clicker.count.toString()
    }
}