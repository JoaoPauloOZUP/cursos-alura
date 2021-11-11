package com.example.agenda.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Student::class,
            parentColumns = ["id"],
            childColumns = ["studentId"],
            onUpdate = CASCADE,
            onDelete = CASCADE
        )
    ]
)
class Phone(
    val numero: String,
    val tipo: TypePhone,
    val studentId: Long
){

    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
}

enum class TypePhone {
    CELULAR, LANDLINE
}