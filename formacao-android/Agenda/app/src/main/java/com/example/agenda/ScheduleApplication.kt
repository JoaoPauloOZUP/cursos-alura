package com.example.agenda

import android.app.Application
import com.example.agenda.dao.StudentDAO
import com.example.agenda.model.Student

class ScheduleApplication : Application() {

    override fun onCreate() {
        val studentDAO = StudentDAO()
        studentDAO.run {
            save(Student("Joao", "199999999", "joao@mail.com"))
            save(Student("Giovana", "1999999", "gio@mail.com"))
            save(Student("Kiara", "1099000", "ki@mail.com"))
        }
        super.onCreate()
    }
}