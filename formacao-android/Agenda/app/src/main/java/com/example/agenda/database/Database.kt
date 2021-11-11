package com.example.agenda.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.agenda.database.ScheduleMigrations.Companion.MIGRATIONS
import com.example.agenda.database.converter.ConvertCalendar
import com.example.agenda.database.room_dao.RoomStudentDAO
import com.example.agenda.model.Student

@Database(
    entities = [Student::class],
    version = 2,
    exportSchema = false
)

@TypeConverters(
    ConvertCalendar::class
)
abstract class Database : RoomDatabase() {
    companion object {
        private const val DATABASE = "Schedule.db"

        fun getInstance(context: Context): com.example.agenda.database.Database {
            return Room.databaseBuilder(
                context,
                com.example.agenda.database.Database::class.java,
                DATABASE
            )
                .allowMainThreadQueries()
                .addMigrations(*MIGRATIONS)
                .build()
        }
    }

    abstract fun getRoomStudentDAO(): RoomStudentDAO
}