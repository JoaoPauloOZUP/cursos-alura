package com.example.agenda

import android.app.Application
import androidx.room.Room
import com.example.agenda.dao.StudentDAO
import com.example.agenda.database.Database
import com.example.agenda.model.Student

class ScheduleApplication : Application() {

    override fun onCreate() {
        val database = Room.databaseBuilder(
            this,
            Database::class.java,
            "Schedule.db"
        )
            .allowMainThreadQueries()
            .build()

        val dao = database.getRoomStudentDAO()

        dao.run {
            save(Student("Joao", "199999999", "joao@mail.com"))
            save(Student("Giovana", "1999999", "gio@mail.com"))
            save(Student("Kiara", "1099000", "ki@mail.com"))
        }
        super.onCreate()
    }
}