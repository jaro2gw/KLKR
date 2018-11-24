package jaro2gw.klkr.database

import android.graphics.Rect
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import jaro2gw.klkr.MainActivity
import jaro2gw.klkr.MainActivity.Action.DELETE
import jaro2gw.klkr.MainActivity.Action.RESET
import jaro2gw.klkr.R
import kotlinx.android.synthetic.main.clicker.view.*

class ClickerAdapter(val context: MainActivity) : RecyclerView.Adapter<ClickerAdapter.ClickerViewHolder>() {
    class ClickerViewHolder(item: View) : RecyclerView.ViewHolder(item)
    class VerticalSpace : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) =
                outRect.set(20, 20, 20, if (parent.getChildAdapterPosition(view) == parent.adapter!!.itemCount - 1) 400 else 0)
    }

    private val inflater = LayoutInflater.from(context)
    private var clickers: List<Clicker>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClickerViewHolder = ClickerViewHolder(inflater.inflate(R.layout.clicker, parent, false))

    override fun onBindViewHolder(holder: ClickerViewHolder, position: Int) {
        with(holder.itemView) {
            with(clickers!![position]) {
                backgroundTintList = ContextCompat.getColorStateList(context, color)
                textView_name.text = name
                textView_count.text = count.toString()
                btn_dec.setOnClickListener { this@ClickerAdapter.context.updateCount(this, -1) }
                btn_inc.setOnClickListener { this@ClickerAdapter.context.updateCount(this, +1) }
                imgBtn_edit.setOnClickListener { this@ClickerAdapter.context.promptEdit(this) }
                imgBtn_reset.setOnClickListener { this@ClickerAdapter.context.promptConfirm(this, RESET) }
                imgBtn_delete.setOnClickListener { this@ClickerAdapter.context.promptConfirm(this, DELETE) }
            }
        }
    }

    override fun getItemCount() = if (clickers == null) 0 else clickers!!.size

    fun setClickers(clickers: List<Clicker>) {
        this.clickers = clickers
        notifyDataSetChanged()
    }
}