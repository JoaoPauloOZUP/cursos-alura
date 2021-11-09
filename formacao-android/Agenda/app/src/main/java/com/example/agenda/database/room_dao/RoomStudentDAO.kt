package com.example.agenda.database.room_dao

import androidx.room.*
import com.example.agenda.model.Student

@Dao
interface RoomStudentDAO {
    @Insert
    fun save(student: Student)

    @Query("SELECT * FROM student")
    fun allStudents(): List<Student>

    @Delete
    fun remove(student: Student)

    @Update
    fun edit(student: Student)
}