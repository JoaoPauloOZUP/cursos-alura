package com.zupacademy.scheduleofstudent.database.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.zupacademy.scheduleofstudent.database.entity.Student
import com.zupacademy.scheduleofstudent.retrofit.service.dto.StudentResponse

@Dao
interface StudentDao {
    @Insert
    fun save(student: Student): Long

    @Query("SELECT * FROM `student` ORDER BY `indice`")
    fun allStudents(): List<Student>

    @Delete
    fun remove(student: Student)

    @Insert(onConflict = REPLACE)
    fun edit(student: Student)

    @Insert(onConflict = REPLACE)
    fun save(student: List<Student>)

    @Query("UPDATE student SET `indice` = :indice WHERE `id` = :id")
    fun updateIndice(id: Long, indice: Int)
}