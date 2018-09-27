package jaro2gw.klkr.model.clicker

import android.os.Parcel
import android.os.Parcelable
import jaro2gw.klkr.R

class Clicker(var name: String? = "Clicker", var count: Int = 0, var color: Int = R.color.colorAccent) : Parcelable {
    constructor(parcel: Parcel) : this(name = parcel.readString(), count = parcel.readInt(), color = parcel.readInt())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        with(parcel) {
            writeString(name)
            writeInt(count)
            writeInt(color)
        }
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Clicker> {
        override fun createFromParcel(parcel: Parcel): Clicker = Clicker(parcel)

        override fun newArray(size: Int): Array<Clicker?> = arrayOfNulls(size)
    }
}
