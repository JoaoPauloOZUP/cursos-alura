package com.example.agenda.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Student(
    var name: String,
    var phone: String,
    var email: String
    ) : Serializable {

    @PrimaryKey(autoGenerate = true)
    var id: Long? = null

    override fun toString(): String {
        return name
    }

    fun isEntity(): Boolean {
        return id?.let { it > 0 } ?: false
    }
}
