package com.example.agenda.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.agenda.R
import com.example.agenda.dao.StudentDAO
import com.example.agenda.model.Student
import com.example.agenda.ui.activity.util.ConstSharedActivities
import com.example.agenda.ui.activity.util.FilterOptionMenu

class FormCreateStudentActivity : AppCompatActivity() {

    companion object {
        private const val TITLE_APPBAR = "New student"
        private const val CREATED_STUDENT = "Created student"
    }

    private val studentDao = StudentDAO()
    private lateinit var nameField: EditText
    private lateinit var phoneField: EditText
    private lateinit var emailField: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_student)
        title = TITLE_APPBAR
        initializeAttributesViews()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_form_student_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val titleOptionMenu = item.title as String
        FilterOptionMenu
            .valueOf(titleOptionMenu.uppercase())
            .action(createStudent(), studentDao)

        Toast.makeText(this, CREATED_STUDENT, ConstSharedActivities.DURATION_TOAST).show()
        finish()
        return super.onOptionsItemSelected(item)
    }

    private fun createStudent(): Student {
        return Student(
            name = nameField.text.toString(),
            phone = phoneField.text.toString(),
            email = emailField.text.toString()
        )
    }

    private fun initializeAttributesViews() {
        nameField = findViewById(R.id.activity_form_students_name)
        phoneField = findViewById(R.id.activity_form_students_phone)
        emailField = findViewById(R.id.activity_form_students_email)
    }
}