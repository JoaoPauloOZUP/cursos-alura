package com.example.agenda.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.agenda.database.room_dao.RoomStudentDAO
import com.example.agenda.model.Student

@Database(
    entities = [Student::class],
    version = 2,
    exportSchema = false
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
                .addMigrations(
                    object : Migration(1, 2) {
                        override fun migrate(database: SupportSQLiteDatabase) {
                            database.execSQL("ALTER TABLE student ADD COLUMN lastname TEXT NOT NULL DEFAULT 0")
                        }
                    }
                )
                .build()
        }
    }

    abstract fun getRoomStudentDAO(): RoomStudentDAO
}