package com.example.agenda

import android.app.Application
import androidx.room.Room
import com.example.agenda.dao.StudentDAO
import com.example.agenda.database.Database
import com.example.agenda.model.Student

class ScheduleApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}