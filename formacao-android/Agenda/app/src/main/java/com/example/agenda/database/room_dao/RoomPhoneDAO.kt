package com.example.agenda.database.room_dao

import androidx.room.Dao
import androidx.room.Query
import com.example.agenda.model.Phone

@Dao
interface RoomPhoneDAO {

    fun save(vararg phone: Phone)

    @Query(
        "SELECT p.* " +
        "FROM Phone p " +
        "JOIN  Student s " +
        "ON p.studentId = s.id " +
        "WHERE p.studentId = :studentId " +
        "LIMIT 1"
    )
    fun findPhone(studentId: Long): Phone
}