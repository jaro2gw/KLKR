package jaro2gw.klkr.database

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.os.AsyncTask

@Database(entities = [Clicker::class], version = 2, exportSchema = false)
abstract class ClickerDB : RoomDatabase() {
    companion object {
        var INSTANCE: ClickerDB? = null

        fun getDatabase(context: Context): ClickerDB {
            synchronized(ClickerDB::class) {
                if (INSTANCE == null) INSTANCE = Room.databaseBuilder(context.applicationContext, ClickerDB::class.java, "clicker_database")
                        .fallbackToDestructiveMigration()
                        .addCallback(roomDBCallback)
                        .build()
            }
            return INSTANCE!!
        }

        private var roomDBCallback = object : RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                PopulateDBAsync(INSTANCE!!).execute()
            }
        }

        class PopulateDBAsync(db: ClickerDB) : AsyncTask<Unit, Unit, Unit>() {
            private val mDAO = db.clickerDAO()

            override fun doInBackground(vararg params: Unit?) {
                mDAO.deleteAll()
                //TODO read database from file
            }
        }
    }

    abstract fun clickerDAO(): ClickerDAO
}