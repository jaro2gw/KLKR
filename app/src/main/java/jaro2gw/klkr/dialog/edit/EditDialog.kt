package jaro2gw.klkr.dialog.edit

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.widget.EditText
import android.widget.GridView
import jaro2gw.klkr.MainActivity
import jaro2gw.klkr.R

class EditDialog : DialogFragment() {
    companion object {
        fun new(position: Int, name: String, count: String, color: Int): EditDialog = with(EditDialog()) {
            arguments = with(Bundle()) {
                putInt("position", position)
                putString("name", name)
                putString("count", count)
                putInt("color", color)
                this
            }
            this
        }
    }

    lateinit var listener: EditListener

    interface EditListener {
        fun editClick(dialog: DialogFragment)
        fun colorSelect(gridView: GridView, adapter: ColorViewAdapter, arguments: Bundle, position: Int)
    }

//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
//            with(inflater.inflate(R.layout.dialog_edit, container, false)) {
//                findViewById<EditText>(R.id.editText_name).setText(arguments!!.getString("name"))
//                findViewById<EditText>(R.id.editText_count).setText(arguments!!.getString("count"))
//                findViewById<GridView>(R.id.gridView_color).adapter = ColorViewAdapter(this@EditDialog.requireContext())
//                return@with this
//            }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog = activity?.let {
        with(AlertDialog.Builder(it)) {
            setCancelable(true)

            setTitle(R.string.edit_msg)

            val inflater = (listener as EditController).context.layoutInflater
            setView(with(inflater.inflate(R.layout.dialog_edit, null)) {
                findViewById<EditText>(R.id.editText_name).setText(arguments!!.getString("name"))
                findViewById<EditText>(R.id.editText_count).setText(arguments!!.getString("count"))
                findViewById<GridView>(R.id.gridView_color).adapter = ColorViewAdapter((listener as EditController).context)
                this
            })

            setPositiveButton(R.string.edit)
            { _, _ -> listener.editClick(this@EditDialog) }

            setNegativeButton(android.R.string.cancel)
            { _, _ -> dialog.cancel() }

            return@let create()
        }
    } ?: throw IllegalStateException("Activity cannot be null")

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try {
            listener = (context as MainActivity).getController().editController
        } catch (e: ClassCastException) {
            throw ClassCastException("Listener has to implement ${listener.javaClass}")
        }
    }
}