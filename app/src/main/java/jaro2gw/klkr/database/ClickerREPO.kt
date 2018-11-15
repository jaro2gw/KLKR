package jaro2gw.klkr.database

import android.app.Application
import android.os.AsyncTask

class ClickerREPO(application: Application) {
    companion object {
        class MyAsyncTask(private val dao: ClickerDAO, private val task: String) : AsyncTask<Clicker, Unit, Unit>() {
            override fun doInBackground(vararg params: Clicker) = when (task) {
                "INSERT" -> dao.insert(params[0])
                "UPDATE" -> dao.update(params[0])
                "DELETE" -> dao.delete(params[0])
                else     -> {
                }
            }
        }

        class ResetAsyncTask(private val dao: ClickerDAO) : AsyncTask<Int, Unit, Unit>() {
            override fun doInBackground(vararg params: Int?) = dao.reset(params[0]!!)
        }
    }

    private var clickerDAO = ClickerDB.getDatabase(application).clickerDAO()

    fun getClickers() = clickerDAO.getClickers()

    fun insert(clicker: Clicker) = MyAsyncTask(clickerDAO, "INSERT").execute(clicker)!!

    fun update(clicker: Clicker) = MyAsyncTask(clickerDAO, "UPDATE").execute(clicker)!!

    fun reset(id: Int) = ResetAsyncTask(clickerDAO).execute(id)!!

    fun delete(clicker: Clicker) = MyAsyncTask(clickerDAO, "DELETE").execute(clicker)!!
}