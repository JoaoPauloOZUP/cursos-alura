package com.zupacademy.scheduleofstudent.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Student(
    @field:PrimaryKey var id: Long,
    var name: String?,
    var phone: String?,
    var email: String?
) : Serializable {

    var indice: Int? = null
}