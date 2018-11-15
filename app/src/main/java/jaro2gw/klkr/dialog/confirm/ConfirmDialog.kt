package jaro2gw.klkr.dialog.confirm

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import jaro2gw.klkr.MainActivity
import jaro2gw.klkr.R
import jaro2gw.klkr.database.Clicker

class ConfirmDialog : DialogFragment() {
    companion object {
        fun get(clicker: Clicker, action: String): ConfirmDialog = with(ConfirmDialog()) {
            arguments = with(Bundle()) {
                putParcelable("clicker", clicker)
                putString("action", action)
                this
            }
            this
        }
    }

    private lateinit var listener: ConfirmListener

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            with(AlertDialog.Builder(it)) {
                setCancelable(true)
                setTitle(arguments!!.getString("message"))

                setMultiChoiceItems(arrayOf(resources.getString(R.string.dont_ask_me_again)), booleanArrayOf(resources.getBoolean(R.bool.dont_ask_me_again)))
                { _, _, isClicked -> listener.choiceSelect(arguments!!, isClicked) }

                setPositiveButton(arguments!!.getString("action"))
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
            listener = (context as MainActivity).confirmListener
            with(arguments!!) {
                putBoolean("choice", resources.getBoolean(R.bool.dont_ask_me_again))
                putString("message", when (getString("action")) {
                    "RESET"  -> resources.getString(R.string.msg_reset)
                    "DELETE" -> resources.getString(R.string.msg_delete)
                    else     -> ""
                })
            }
        }
        catch (e: ClassCastException) {
            throw ClassCastException("Listener has to implement ${listener.javaClass}")
        }
    }
}