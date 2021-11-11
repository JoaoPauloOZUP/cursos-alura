package com.example.agenda.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.agenda.model.TypePhone

class ScheduleMigrations {
    companion object {
        val MIGRATIONS = migrations(
            migrationOnetoTwo(),
            migrationTwotoThree()
        )
    }
}

private fun migrations(vararg migrations: Migration) = migrations

private fun migrationOnetoTwo() = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE Student ADD COLUMN phoneCelular TEXT NOT NULL DEFAULT 0")
    }
}

private fun migrationTwotoThree() = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "CREATE TABLE IF NOT EXISTS Student_new (name TEXT NOT NULL, email TEXT NOT NULL, id INTEGER PRIMARY KEY AUTOINCREMENT, createdAt INTEGER NOT NULL)"
        )

        database.execSQL(
            "INSERT INTO Student_new (name, email, id, createdAt) SELECT name, email, id, createdAt FROM Student"
        )

        database.execSQL(
            "CREATE TABLE IF NOT EXISTS Phone (numero TEXT NOT NULL, tipo TEXT NOT NULL DEFAULT 0, studentId INTEGER NOT NULL, id INTEGER PRIMARY KEY AUTOINCREMENT, FOREIGN KEY(studentId) REFERENCES Student(id) ON UPDATE CASCADE ON DELETE CASCADE )"
        )

        database.execSQL(
            "INSERT INTO Phone (numero, studentId) SELECT phone, id FROM Student"
        )

        database.execSQL(
            "UPDATE Phone SET Tipo = ? ", arrayOf(TypePhone.LANDLINE)
        )

        database.execSQL(
            "DROP TABLE Student"
        )

        database.execSQL(
            "ALTER TABLE Student_new RENAME TO Student"
        )

    }
}