package com.zupacademy.praticaschedulestudent.model

import java.io.Serializable

class Student(
    var name: String,
    var phone: String,
    var email: String
) : Serializable {

    var id: Long? = null

    fun isEntity(): Boolean {
        return id?.let { it > 0 } ?: false
    }

    override fun toString(): String {
        return name
    }
}
