package com.example.agenda.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class ScheduleMigrations {
    companion object {
        val MIGRATIONS = migrations(
            migrationOnetoTwo()
        )
    }
}

private fun migrations(vararg migrations: Migration) = migrations

private fun migrationOnetoTwo() = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE Student ADD COLUMN phoneCelular TEXT NOT NULL DEFAULT 0")
    }
}