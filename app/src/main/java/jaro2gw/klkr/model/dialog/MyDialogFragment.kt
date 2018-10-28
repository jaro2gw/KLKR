package jaro2gw.klkr.model.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import jaro2gw.klkr.MainActivity

class MyDialogFragment : DialogFragment() {
    private lateinit var listener: MyDialogListener

    interface MyDialogListener {
        fun onDialogPositiveClick(dialog: DialogFragment)
        fun onDialogNegativeClick(dialog: DialogFragment)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            AlertDialog.Builder(it)
                    .setCancelable(true)
                    .setMessage(arguments!!.getString("message"))
                    .setPositiveButton(arguments!!.getString("action"))
                    { dialog, which -> listener.onDialogPositiveClick(this) }
//                    .setNegativeButton(arguments!!.getString("negative"))
                    .setNegativeButton(android.R.string.cancel)
                    { dialog, which -> listener.onDialogNegativeClick(this) }
                    .create()
//            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try {
            listener = (context as MainActivity).controller
        } catch (e: ClassCastException) {
            throw ClassCastException(context.toString() + " must implement ${listener.javaClass}")
        }
    }
}