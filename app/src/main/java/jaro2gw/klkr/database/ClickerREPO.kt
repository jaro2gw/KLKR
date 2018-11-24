package jaro2gw.klkr.database

import android.app.Application
import android.os.AsyncTask

class ClickerREPO(application: Application) {
    private var clickerDAO = ClickerDB.getDatabase(application).clickerDAO()

    fun getClickers() = clickerDAO.getClickers()

    fun insert(clicker: Clicker) = AsyncTask.execute { clickerDAO.insert(clicker) }

    fun update(clicker: Clicker) = AsyncTask.execute { clickerDAO.update(clicker) }

    fun reset(id: Int) = AsyncTask.execute { clickerDAO.reset(id) }

    fun delete(clicker: Clicker) = AsyncTask.execute { clickerDAO.delete(clicker) }
}