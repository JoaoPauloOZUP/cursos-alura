package com.zupacademy.scheduleofstudent.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zupacademy.scheduleofstudent.database.dao.StudentDao
import com.zupacademy.scheduleofstudent.database.entity.Student

@Database(
    entities = [Student::class],
    version = 2,
    exportSchema = true
)
abstract class Database : RoomDatabase() {
    abstract fun getStudentDAO(): StudentDao
}