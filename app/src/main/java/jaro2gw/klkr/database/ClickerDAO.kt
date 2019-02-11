package jaro2gw.klkr.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

@Dao
interface ClickerDAO {
    @Query("SELECT * FROM clicker_table ORDER BY id ASC")
    fun getClickers(): LiveData<List<Clicker>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(clicker: Clicker)

    @Update
    fun update(clicker: Clicker)

    @Query("UPDATE clicker_table SET count = 0 WHERE id = :id")
    fun reset(id: Int)

    @Delete
    fun delete(clicker: Clicker)

    @Query("DELETE FROM clicker_table")
    fun deleteAll()
}