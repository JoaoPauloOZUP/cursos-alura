package com.example.agenda.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Phone(
    val numero: String,
    val tipo: TypePhone
){

    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
}

enum class TypePhone {
    CELULAR, LANDLINE
}