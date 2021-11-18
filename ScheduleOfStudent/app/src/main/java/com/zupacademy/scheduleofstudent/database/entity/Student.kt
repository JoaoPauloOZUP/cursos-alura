package com.zupacademy.scheduleofstudent.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
class Student(
    var name: String,
    var phone: String,
    var email: String
) : Serializable {

    @PrimaryKey(autoGenerate = true)
    var id: Long? = null

    var indice: Int? = null
}