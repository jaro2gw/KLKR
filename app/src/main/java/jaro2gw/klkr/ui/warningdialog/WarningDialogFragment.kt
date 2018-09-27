package jaro2gw.klkr.ui.warningdialog

import android.app.Dialog
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import jaro2gw.klkr.R


class WarningDialogFragment : DialogFragment() {

    companion object {
        fun newInstance() = WarningDialogFragment()
    }

    private lateinit var viewModel: WarningDialogViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.warning_dialog_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(WarningDialogViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use the Builder class for convenient dialog construction
        val builder = AlertDialog.Builder(activity!!)
        builder.setMessage("PLEASE CONFIRM")
                .setPositiveButton("CONFIRM") { dialog, id ->
                    println("CONFIRMED")
                }
                .setNegativeButton("CANCEL") { dialog, id ->
                    println("CANCELED")
                }
        // Create the AlertDialog object and return it
        return builder.create()
    }
}
