package com.example.agenda.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Apoio: https://lucas-marciano.medium.com/por-que-usar-o-parcelable-ao-inv%C3%A9s-do-serializable-5f7543a9c7f3
 * Documentation: https://developer.android.com/reference/android/os/Parcelable.html#kotlin
 * */
data class Student(
    val name: String,
    val phone: String,
    val email: String
    ) : Parcelable {

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Student?> = object : Parcelable.Creator<Student?> {
            override fun createFromParcel(source: Parcel?): Student? {
                return Student(source!!)
            }

            override fun newArray(size: Int): Array<Student?> {
                return arrayOfNulls(size)
            }
        }
    }

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun toString(): String {
        return name
    }

    override fun describeContents(): Int {
        return 0;
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(name)
        dest?.writeString(phone)
        dest?.writeString(email)
    }
}
