package jaro2gw.klkr.database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import jaro2gw.klkr.dialog.customize.ColorViewAdapter

@Entity(tableName = "clicker_table")
data class Clicker(@ColumnInfo(name = "name") var name: String = "Clicker",
                   @ColumnInfo(name = "count") var count: Int = 0,
                   @ColumnInfo(name = "color") var color: Int = ColorViewAdapter.colors[0],
                   @PrimaryKey(autoGenerate = true) var id: Int = 0) : Parcelable {
    companion object CREATOR : Parcelable.Creator<Clicker> {
        override fun createFromParcel(parcel: Parcel): Clicker = Clicker(parcel)

        override fun newArray(size: Int): Array<Clicker?> = arrayOfNulls(size)
    }

    @Ignore
    constructor(parcel: Parcel) : this(parcel.readString()!!, parcel.readInt(), parcel.readInt(), parcel.readInt())

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        with(dest!!) {
            writeString(name)
            writeInt(count)
            writeInt(color)
            writeInt(id)
        }
    }

    override fun describeContents() = 0

    override fun toString(): String = "$name $count $color $id"
}