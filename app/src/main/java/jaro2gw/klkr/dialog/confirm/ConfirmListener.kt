package jaro2gw.klkr.dialog.confirm

import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import jaro2gw.klkr.MainActivity
import jaro2gw.klkr.database.Clicker

class ConfirmListener(private val context: MainActivity) {
    fun choiceSelect(arguments: Bundle, checked: Boolean) = arguments.putBoolean("choice", checked)

    private fun performActionWithChoice(clicker: Clicker, action: String, choice: Boolean) = with(context) {
        performAction(clicker, action)
        getPreferences(Context.MODE_PRIVATE)
                .edit()
                .putBoolean(action, choice)
                .apply()
    }

    fun performAction(clicker: Clicker, action: String) = with(context) {
        when (action) {
            "RESET"  -> reset(clicker)
            "DELETE" -> delete(clicker)
            else     -> {
            }
        }
    }

    fun confirmClick(dialog: DialogFragment) = with(dialog.arguments!!) {
        performActionWithChoice(getParcelable("clicker")!!, getString("action")!!, getBoolean("choice"))
    }
}