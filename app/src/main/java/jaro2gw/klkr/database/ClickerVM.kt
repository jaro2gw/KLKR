package jaro2gw.klkr.database

import android.app.Application
import android.arch.lifecycle.AndroidViewModel

class ClickerVM(application: Application) : AndroidViewModel(application) {
    private var clickerREPO = ClickerREPO(application)
    internal var clickers = clickerREPO.getClickers()

    fun insert(clicker: Clicker) = clickerREPO.insert(clicker)

    fun update(clicker: Clicker) = clickerREPO.update(clicker)

    fun reset(id: Int) = clickerREPO.reset(id)

    fun delete(clicker: Clicker) = clickerREPO.delete(clicker)
}