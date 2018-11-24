package jaro2gw.klkr.dialog.confirm

import android.content.Context
import android.support.v4.app.DialogFragment
import jaro2gw.klkr.MainActivity
import jaro2gw.klkr.MainActivity.Action.DELETE
import jaro2gw.klkr.MainActivity.Action.RESET
import jaro2gw.klkr.database.Clicker

class ConfirmListener(private val context: MainActivity) {
    private fun performActionWithChoice(clicker: Clicker, action: MainActivity.Action, choice: Boolean) = with(context) {
        performAction(clicker, action)
        getPreferences(Context.MODE_PRIVATE).edit().putBoolean(action.name, choice).apply()
    }

    fun performAction(clicker: Clicker, action: MainActivity.Action) = with(context) {
        if (action == RESET) reset(clicker)
        else if (action == DELETE) delete(clicker)
    }

    fun confirmClick(dialog: DialogFragment) = with(dialog.arguments!!) {
        performActionWithChoice(
                getParcelable("clicker")!!,
                getSerializable("action") as MainActivity.Action,
                getBoolean("choice"))
    }
}