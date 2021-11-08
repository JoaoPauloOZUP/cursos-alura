package com.example.agenda.database.room_dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.agenda.model.Student

@Dao
interface RoomStudentDAO {
    @Insert
    fun save(student: Student)

    @Query("SELECT * FROM student")
    fun allStudents(): List<Student>

    @Delete
    fun remove(student: Student)
}