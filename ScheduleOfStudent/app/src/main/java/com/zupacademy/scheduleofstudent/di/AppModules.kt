package com.zupacademy.scheduleofstudent.di

import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.google.firebase.messaging.FirebaseMessaging
import com.zupacademy.scheduleofstudent.database.Database
import com.zupacademy.scheduleofstudent.database.dao.StudentDao
import com.zupacademy.scheduleofstudent.database.entity.Student
import com.zupacademy.scheduleofstudent.database.repository.LoadedDataListener
import com.zupacademy.scheduleofstudent.database.repository.StudentRepository
import com.zupacademy.scheduleofstudent.retrofit.StudentRetrofit
import com.zupacademy.scheduleofstudent.retrofit.service.StudentService
import com.zupacademy.scheduleofstudent.ui.adapter.StudentRecyclerAdapter
import org.koin.dsl.module

private const val DATABASE_NAME = "Schedule.db"
val appModules = module {
    single<Database> {
        Room.databaseBuilder(
            get(),
            Database::class.java,
            DATABASE_NAME
        )
            .addMigrations(
                object : Migration(1, 2) {
                    override fun migrate(database: SupportSQLiteDatabase) {
                        database.execSQL("ALTER TABLE student ADD COLUMN indice INTEGER")
                    }
                },
                object : Migration(2, 3) {
                    override fun migrate(database: SupportSQLiteDatabase) {
                        database.execSQL("DROP TABLE student")
                        database.execSQL("CREATE TABLE IF NOT EXISTS student (id INTEGER PRIMARY KEY NOT NULL, name TEXT, phone TEXT, email TEXT, indice INTEGER)")
                    }
                }
            )
            .build()
    }

    single<StudentDao> {
        get<Database>().getStudentDAO()
    }

    single<StudentRetrofit> {
        StudentRetrofit()
    }

    single<StudentService> {
        get<StudentRetrofit>().studentService
    }

    single<StudentRepository> {
        StudentRepository(get(), get(), get())
    }
}