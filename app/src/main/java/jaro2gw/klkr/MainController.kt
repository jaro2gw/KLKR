package jaro2gw.klkr

import android.content.Intent
import jaro2gw.klkr.dialog.confirm.ConfirmController
import jaro2gw.klkr.dialog.confirm.ConfirmDialog
import jaro2gw.klkr.dialog.edit.EditActivity

class MainController(private val context: MainActivity) {
    lateinit var confirmController: ConfirmController

    val map = with(HashMap<String, Boolean>()) {
        put("RESET", false)
        put("DELETE", false)
        return@with this
    }

    fun promptEdit(position: Int) = with(context.clickerList[position]) {
        context.startActivityForResult(Intent(context, EditActivity::class.java)
                .putExtra("position", position)
                .putExtra("name", name)
                .putExtra("count", count.toString())
                .putExtra("color", color), 1)
    }

    fun promptConfirmation(position: Int, action: String) = if (!map[action]!!) {
        ConfirmDialog.new(position, action).show(this@MainController.context.supportFragmentManager, "CONFIRM")
    } else confirmController.performAction(position, action)
}