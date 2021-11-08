package com.example.agenda.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.agenda.database.room_dao.RoomStudentDAO
import com.example.agenda.model.Student

@Database(
    entities = [Student::class],
    version = 1,
    exportSchema = false
)
abstract class Database : RoomDatabase() {
    abstract fun getRoomStudentDAO(): RoomStudentDAO
}