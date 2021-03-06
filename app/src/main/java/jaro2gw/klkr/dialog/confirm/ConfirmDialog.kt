package jaro2gw.klkr.dialog.confirm

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import jaro2gw.klkr.MainActivity
import jaro2gw.klkr.MainActivity.Action.DELETE
import jaro2gw.klkr.MainActivity.Action.RESET
import jaro2gw.klkr.R
import jaro2gw.klkr.database.Clicker

class ConfirmDialog : DialogFragment() {
    companion object {
        fun get(clicker: Clicker, action: MainActivity.Action): ConfirmDialog = with(ConfirmDialog()) {
            arguments = with(Bundle()) {
                putParcelable("clicker", clicker)
                putSerializable("action", action)
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
                { _, _, isClicked -> arguments!!.putBoolean("choice", isClicked) }

                setPositiveButton(arguments!!.getSerializable("action").let { action -> action as MainActivity.Action }.name)
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
                putString("message", when (getSerializable("action") as MainActivity.Action) {
                    RESET  -> resources.getString(R.string.msg_reset)
                    DELETE -> resources.getString(R.string.msg_delete)
                    else   -> ""
                })
            }
        }
        catch (e: ClassCastException) {
            throw ClassCastException("Listener has to implement ${listener.javaClass}")
        }
    }
}