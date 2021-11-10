package com.example.agenda.database.converter

import androidx.room.TypeConverter
import java.util.*

class ConvertCalendar {

    @TypeConverter
    fun toLong(calendar: Calendar) = calendar.timeInMillis

    @TypeConverter
    fun toCalendar(value: Long?): Calendar? {
        return Calendar.getInstance().let { instanceCalendar ->
            value?.let { value ->
                instanceCalendar.timeInMillis = value
            }
            return@let instanceCalendar
        }
    }
}