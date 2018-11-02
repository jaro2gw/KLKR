package jaro2gw.klkr.dialog.confirm

import android.support.v4.app.DialogFragment
import jaro2gw.klkr.MainActivity

class ConfirmController(private val context: MainActivity) : ConfirmDialog.ConfirmListener {
    val tempMap = HashMap<String, Boolean>()

    override fun choiceSelect(action: String, checked: Boolean) {
        tempMap[action] = checked
    }

    private fun reset(position: Int) = context.clickerList[position].reset()

    private fun delete(position: Int) = context.clickerList.removeAt(position)

    fun performAction(position: Int, action: String) {
        when (action) {
            "RESET"  -> reset(position)
            "DELETE" -> delete(position)
        }
        context.updateList()
    }

    override fun confirmClick(dialog: DialogFragment) = with(dialog.arguments!!) {
        context.getController().map.putAll(tempMap)
        tempMap.clear()
        performAction(getInt("position"), this["action"] as String)
    }
}