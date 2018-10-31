package jaro2gw.klkr.model.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import jaro2gw.klkr.MainActivity
import jaro2gw.klkr.R

class EditDialog : DialogFragment() {
    private lateinit var listener: EditListener

    interface EditListener {
        fun editClick(dialog: DialogFragment)
        fun cancelClick(dialog: DialogFragment)
//        fun multipleChoiceSelect(action: String, checked: Boolean)
//        fun singleChoiceSelect(action: String)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let { it ->
            AlertDialog.Builder(it)
                    .setCancelable(true)
                    .setMessage(R.string.edit_msg)

//                    .setSingleChoiceItems(Array(1) { "Don't ask me again" }, 0)
//                    { _, _ -> listener.singleChoiceSelect(arguments!!["action"] as String) }
//
//                    .setMultiChoiceItems(Array(1) { "Don't ask me again" }, BooleanArray(1))
//                    { _, _, isClicked -> listener.multipleChoiceSelect(arguments!!["action"] as String, isClicked) }

                    .setPositiveButton(R.string.edit)
                    { _, _ -> listener.editClick(this) }

                    .setNegativeButton(android.R.string.cancel)
                    { _, _ -> listener.cancelClick(this) }

                    .create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try {
            listener = (context as MainActivity).controller
        } catch (e: ClassCastException) {
            throw ClassCastException("Listener has to implement ${listener.javaClass}")
        }
    }
}