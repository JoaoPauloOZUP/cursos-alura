package com.example.agenda.model

import java.io.Serializable

data class Student(
    var name: String,
    var phone: String,
    var email: String
    ) : Serializable {

    var id: Long? = null

    override fun toString(): String {
        return name
    }
}
