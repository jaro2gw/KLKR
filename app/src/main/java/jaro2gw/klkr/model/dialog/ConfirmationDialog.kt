package jaro2gw.klkr.model.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import jaro2gw.klkr.MainActivity

class ConfirmationDialog : DialogFragment() {
    private lateinit var listener: ConfirmationListener

    interface ConfirmationListener {
        fun confirmClick(dialog: DialogFragment)
        fun cancelClick(dialog: DialogFragment)
        fun multipleChoiceSelect(action: String, checked: Boolean)
        fun singleChoiceSelect(action: String)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let { it ->
            AlertDialog.Builder(it)
                    .setCancelable(true)
                    .setMessage(arguments!!.getString("message"))

                    .setSingleChoiceItems(Array(1) { "Don't ask me again" }, 0)
                    { _, _ -> listener.singleChoiceSelect(arguments!!["action"] as String) }

                    .setMultiChoiceItems(Array(1) { "Don't ask me again" }, BooleanArray(1))
                    { _, _, isClicked -> listener.multipleChoiceSelect(arguments!!["action"] as String, isClicked) }

                    .setPositiveButton(arguments!!["action"] as String)
                    { _, _ -> listener.confirmClick(this) }

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