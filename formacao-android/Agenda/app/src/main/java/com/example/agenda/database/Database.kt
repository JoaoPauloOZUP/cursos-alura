package com.example.agenda.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.agenda.database.converter.ConvertCalendar
import com.example.agenda.database.room_dao.RoomStudentDAO
import com.example.agenda.model.Student

@Database(
    entities = [Student::class],
    version = 4,
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
                .addMigrations(
                    object : Migration(1, 2) {
                        override fun migrate(database: SupportSQLiteDatabase) {
                            database.execSQL("ALTER TABLE Student ADD COLUMN lastname TEXT NOT NULL DEFAULT 0")
                        }
                    },
                    // Migrando para versao anterior do banco depois de uma alteracao
                    object : Migration(2, 3) {
                        override fun migrate(database: SupportSQLiteDatabase) {
                            // Criar nova tabela com as informacoes desejadas
                            database.execSQL(
                                "CREATE TABLE IF NOT EXISTS Student_new (name TEXT NOT NULL, phone TEXT NOT NULL, email TEXT NOT NULL, id INTEGER PRIMARY KEY AUTOINCREMENT)"
                            )

                            // Copiar dados da tabela antiga para a nova
                            database.execSQL(
                                "INSERT INTO Student_new (name, phone, email, id) SELECT name, phone, email, id FROM Student"
                            )

                            // Remover tabela antiga
                            database.execSQL(
                                "DROP TABLE Student"
                            )

                            // Renomear a tabela nova com nome da tabela antiga
                            database.execSQL(
                                "ALTER TABLE Student_new RENAME TO Student"
                            )
                        }
                    },
                    object : Migration(3, 4) {
                        override fun migrate(database: SupportSQLiteDatabase) {
                            database.execSQL("ALTER TABLE Student ADD COLUMN createdAt INTEGER")
                        }
                    }
                )
                .build()
        }
    }

    abstract fun getRoomStudentDAO(): RoomStudentDAO
}