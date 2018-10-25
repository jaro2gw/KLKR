package jaro2gw.klkr.model.clicker

import android.os.Bundle
import android.support.v4.app.DialogFragment
import jaro2gw.klkr.MainActivity
import jaro2gw.klkr.R
import jaro2gw.klkr.model.dialog.MyDialogFragment

class ClickerController(val context: MainActivity) : MyDialogFragment.MyDialogListener {
    override fun onDialogPositiveClick(dialog: DialogFragment) {
        with(dialog.arguments!!) {
            when (this["action"]) {
                "RESET"  -> reset(this.getInt("position"))
                "DELETE" -> delete(this.getInt("position"))
            }
        }
        dialog.dismiss()
    }

    override fun onDialogNegativeClick(dialog: DialogFragment) = dialog.dismiss()

    fun edit(position: Int) {
        context.clickerList[position].edit("Clicker", 0, R.color.colorAccent)
    }

    private fun reset(position: Int) {
        context.clickerList[position].reset()
    }

    private fun delete(position: Int) {
        context.clickerList.removeAt(position)
        context.updateList()
    }

    fun prompt(position: Int, action: String) {
        android.R.string.cancel
        val dialog = MyDialogFragment()

        dialog.arguments = Bundle()
        with(dialog.arguments!!) {
            putInt("position", position)
            putString("action", action)
            putString("positive", action)
            putString("negative", context.resources.getString(android.R.string.cancel))
            putString("message", when (action) {
                "RESET"  -> context.resources.getString(R.string.msg_reset)
                "DELETE" -> context.resources.getString(R.string.msg_delete)
                else     -> ""
            })
        }

        dialog.show(context.supportFragmentManager, "PROMPT")
    }
}