package jaro2gw.klkr.model.clicker

import jaro2gw.klkr.MainActivity
import jaro2gw.klkr.R

class ClickerController(val context: MainActivity) {
    fun edit(position: Int) {
        context.clickerList[position].edit("Clicker", 0, R.color.colorAccent)
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun reset(position: Int) {
        context.clickerList[position].reset()
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun delete(position: Int) {
        context.clickerList.removeAt(position)
        context.updateList()
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}