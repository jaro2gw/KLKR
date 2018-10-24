package jaro2gw.klkr.model.dialog

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog

class MyDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(context!!)
        builder.setCancelable(true)
        builder.setMessage(arguments!!["message"] as String)
        builder.setPositiveButton(arguments!!["positive"] as String) { dialog, which -> }
        builder.setNegativeButton(arguments!!["negative"] as String) { dialog, which -> onCancel(dialog) }
        return builder.create()
    }
}