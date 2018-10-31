package jaro2gw.klkr.model.clicker

import android.os.Bundle
import android.support.v4.app.DialogFragment
import jaro2gw.klkr.MainActivity
import jaro2gw.klkr.R
import jaro2gw.klkr.model.dialog.ConfirmationDialog
import jaro2gw.klkr.model.dialog.EditDialog

class ClickerController(private val context: MainActivity) : ConfirmationDialog.ConfirmationListener, EditDialog.EditListener {
    private val map = with(HashMap<String, Boolean>()) {
        put("RESET", false)
        put("DELETE", false)
        this
    }

    private val tempMap = HashMap<String, Boolean>()

    override fun singleChoiceSelect(action: String) {
        tempMap[action] = true
    }

    override fun multipleChoiceSelect(action: String, checked: Boolean) {
        tempMap[action] = checked
    }

    private fun edit(position: Int, name: String, count: Int, color: Int) {
        context.clickerList[position].edit(name, count, color)
        context.updateList()
    }

    override fun editClick(dialog: DialogFragment) = with(dialog.arguments!!) {
        edit(getInt("position"), this["name"] as String, getInt("count"), getInt("color"))
    }

    fun promptEdit(position: Int) {
        val dialog = EditDialog()
        val clicker = context.clickerList[position]
        dialog.arguments = Bundle()
        with(dialog.arguments!!) {
            putString("name", clicker.name)
            putInt("count", clicker.count)
            putInt("color", clicker.color)
            putInt("position", position)
        }

        dialog.show(context.supportFragmentManager, "EDIT")
    }

    private fun reset(position: Int) {
        context.clickerList[position].reset()
    }

    private fun delete(position: Int) {
        context.clickerList.removeAt(position)
        context.updateList()
    }

    private fun performAction(position: Int, action: String) {
        when (action) {
            "RESET"  -> reset(position)
            "DELETE" -> delete(position)
        }
    }


    override fun confirmClick(dialog: DialogFragment) = with(dialog.arguments!!) {
        map.putAll(tempMap)
        performAction(getInt("position"), this["action"] as String)
    }

    override fun cancelClick(dialog: DialogFragment) = dialog.dismiss()

    fun promptConfirmation(position: Int, action: String) = if (!map[action]!!) {
        with(tempMap) {
            put("RESET", false)
            put("DELETE", false)
        }

        val dialog = ConfirmationDialog()

        dialog.arguments = Bundle()
        with(dialog.arguments!!) {
            putInt("position", position)
            putString("action", action)
            putString("message", when (action) {
                "RESET"  -> context.resources.getString(R.string.msg_reset)
                "DELETE" -> context.resources.getString(R.string.msg_delete)
                else     -> ""
            })
        }

        dialog.show(context.supportFragmentManager, "CONFIRM")
    } else performAction(position, action)
}