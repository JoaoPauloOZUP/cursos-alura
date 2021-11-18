package com.zupacademy.scheduleofstudent.di

import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.zupacademy.scheduleofstudent.database.Database
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
                }
            )
            .build()
    }
}