package jaro2gw.klkr.dialog.confirm

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import jaro2gw.klkr.MainActivity
import jaro2gw.klkr.R

class ConfirmDialog : DialogFragment() {
    companion object {
        fun new(position: Int, action: String): ConfirmDialog = with(ConfirmDialog()) {
            arguments = with(Bundle()) {
                putInt("position", position)
                putString("action", action)
                this
            }
            this
        }
    }

    private lateinit var listener: ConfirmListener

    interface ConfirmListener {
        fun confirmClick(dialog: DialogFragment)
        fun choiceSelect(action: String, checked: Boolean)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            with(AlertDialog.Builder(it)) {
                setCancelable(true)
                setTitle(arguments!!.getString("message"))

                setMultiChoiceItems(arrayOf("Don't ask me again"), booleanArrayOf(false))
                { _, _, isClicked -> listener.choiceSelect(arguments!!["action"] as String, isClicked) }

                setPositiveButton(arguments!!["action"] as String)
                { _, _ -> listener.confirmClick(this@ConfirmDialog) }

                setNegativeButton(android.R.string.cancel)
                { _, _ -> dialog.cancel() }
                return@let create()
            }
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try {
            listener = (context as MainActivity).getController().confirmController
            with(arguments!!) {
                putString("message", when (this["action"]) {
                    "RESET"  -> context.resources.getString(R.string.msg_reset)
                    "DELETE" -> context.resources.getString(R.string.msg_delete)
                    else     -> ""
                })
            }
        } catch (e: ClassCastException) {
            throw ClassCastException("Listener has to implement ${listener.javaClass}")
        }
    }
}