package com.wxd.firstlinecode.skills

import android.os.Parcel
import android.os.Parcelable

class PersonP() : Parcelable {

    var name = ""
    var age = 0

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(name)
        dest?.writeInt(age)
    }

    companion object CREATOR : Parcelable.Creator<PersonP> {
        override fun createFromParcel(parcel: Parcel): PersonP {
            val person = PersonP()
            person.name = parcel.readString() ?: ""
            person.age = parcel.readInt()
            return person
        }

        override fun newArray(size: Int): Array<PersonP?> {
            return arrayOfNulls(size)
        }
    }
}