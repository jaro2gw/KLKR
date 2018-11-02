package jaro2gw.klkr

import jaro2gw.klkr.MainActivity
import jaro2gw.klkr.dialog.confirm.ConfirmController
import jaro2gw.klkr.dialog.confirm.ConfirmDialog
import jaro2gw.klkr.dialog.edit.EditController
import jaro2gw.klkr.dialog.edit.EditDialog

class MainController(val context: MainActivity) {
    lateinit var confirmController: ConfirmController
    lateinit var editController: EditController

    fun initControllers() {
        confirmController = ConfirmController(context)
        editController = EditController(context)
    }

    val map = with(HashMap<String, Boolean>()) {
        put("RESET", false)
        put("DELETE", false)
        return@with this
    }

    fun promptEdit(position: Int) = with(context.clickerList[position]) {
        EditDialog.new(position, name, count.toString(), color)
    }.show(this@MainController.context.supportFragmentManager, "EDIT")

    fun promptConfirmation(position: Int, action: String) = if (!map[action]!!) {
        ConfirmDialog.new(position, action).show(this@MainController.context.supportFragmentManager, "CONFIRM")
    } else confirmController.performAction(position, action)
}