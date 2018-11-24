package jaro2gw.klkr.database

import android.app.Application
import android.os.AsyncTask

class ClickerREPO(application: Application) {
    enum class Task { INSERT, UPDATE, DELETE }
    companion object {
        class MyAsyncTask(private val dao: ClickerDAO, private val task: Task) : AsyncTask<Clicker, Unit, Unit>() {
            override fun doInBackground(vararg params: Clicker) = when (task) {
                Task.INSERT -> dao.insert(params[0])
                Task.UPDATE -> dao.update(params[0])
                Task.DELETE -> dao.delete(params[0])
            }
        }

        class ResetAsyncTask(private val dao: ClickerDAO) : AsyncTask<Int, Unit, Unit>() {
            override fun doInBackground(vararg params: Int?) = dao.reset(params[0]!!)
        }
    }

    private var clickerDAO = ClickerDB.getDatabase(application).clickerDAO()

    fun getClickers() = clickerDAO.getClickers()

    fun insert(clicker: Clicker) = MyAsyncTask(clickerDAO, Task.INSERT).execute(clicker)!!

    fun update(clicker: Clicker) = MyAsyncTask(clickerDAO, Task.UPDATE).execute(clicker)!!

    fun reset(id: Int) = ResetAsyncTask(clickerDAO).execute(id)!!

    fun delete(clicker: Clicker) = MyAsyncTask(clickerDAO, Task.DELETE).execute(clicker)!!
}