package com.example.agenda.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

@Entity
data class Student(
    var name: String,
//    var lastname: String,
    var phone: String,
    var email: String
    ) : Serializable {

    @PrimaryKey(autoGenerate = true)
    var id: Long? = null

    var createdAt: Calendar = Calendar.getInstance()

    fun fullName(): String {
//        return "$name $lastname"
        return name
    }

    fun createdDate(): String {
        val formatDate = SimpleDateFormat("dd/MM/yyyy")
        return formatDate.format(createdAt.time)
    }

    override fun toString(): String {
        return name
    }

    fun isEntity(): Boolean {
        return id?.let { it > 0 } ?: false
    }
}
