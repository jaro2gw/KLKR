package jaro2gw.klkr.dialog.confirm

import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import jaro2gw.klkr.MainActivity

class ConfirmListener(private val context: MainActivity) {
    fun choiceSelect(arguments: Bundle, checked: Boolean) = arguments.putBoolean("choice", checked)

    private fun reset(position: Int) = context.getClickers()[position].reset()

    private fun delete(position: Int) = context.getClickers().removeAt(position)

    private fun performActionWithChoice(position: Int, action: String, choice: Boolean) = with(context) {
        performAction(position, action)
        getPreferences(Context.MODE_PRIVATE)
                .edit()
                .putBoolean(action, choice)
                .apply()
    }

    fun performAction(position: Int, action: String) = with(context) {
        when (action) {
            "RESET"  -> reset(position)
            "DELETE" -> delete(position)
        }
        updateList()
    }

    fun confirmClick(dialog: DialogFragment) = with(dialog.arguments!!) {
        performActionWithChoice(getInt("position"), getString("action")!!, getBoolean("choice"))
    }
}