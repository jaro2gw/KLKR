package jaro2gw.klkr.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = [Clicker::class], version = 1, exportSchema = false)
abstract class ClickerDB : RoomDatabase() {
    companion object {
        private var INSTANCE: ClickerDB? = null

        fun getDatabase(context: Context): ClickerDB {
            synchronized(ClickerDB::class) {
                if (INSTANCE == null) INSTANCE = Room.databaseBuilder(context.applicationContext, ClickerDB::class.java, "clicker_database")
                        .fallbackToDestructiveMigration()
                        .build()
            }
            return INSTANCE!!
        }
    }

    abstract fun clickerDAO(): ClickerDAO
}