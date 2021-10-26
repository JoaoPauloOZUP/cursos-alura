package com.zupacademy.praticaschedulestudent

import android.app.Application
import com.zupacademy.praticaschedulestudent.model.Student
import com.zupacademy.praticaschedulestudent.repository.StudentRepository

class ScheduleApplication : Application() {

    override fun onCreate() {
        StudentRepository().run {
            save(Student("Joao Paulo", "988447795", "mail@mail.com"))
            save(Student("Giovana", "988447795", "mail@mail.com"))
            save(Student("Pablo", "988447795", "mail@mail.com"))
            save(Student("Nita", "988447795", "mail@mail.com"))
            save(Student("Rafael dos Anjos", "988447795", "mail@mail.com"))
            save(Student("Guilherme", "988447795", "mail@mail.com"))
            save(Student("Pedro", "988447795", "mail@mail.com"))
            save(Student("Vitor", "988447795", "mail@mail.com"))
            save(Student("Paulo", "988447795", "mail@mail.com"))
            save(Student("Gabriel Dias", "988447795", "mail@mail.com"))
            save(Student("Antonio Nunes", "988447795", "mail@mail.com"))
            save(Student("Marcos Paulo", "988447795", "mail@mail.com"))
            save(Student("William Borges", "988447795", "mail@mail.com"))
        }
        super.onCreate()
    }
}