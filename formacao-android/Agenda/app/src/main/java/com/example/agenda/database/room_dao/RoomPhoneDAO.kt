package com.example.agenda.database.room_dao

import androidx.room.Dao
import com.example.agenda.model.Phone

@Dao
interface RoomPhoneDAO {
    fun findPhone(): Phone
}