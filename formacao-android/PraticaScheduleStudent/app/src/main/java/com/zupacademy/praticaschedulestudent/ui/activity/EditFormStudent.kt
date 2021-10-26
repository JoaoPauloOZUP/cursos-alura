package com.zupacademy.praticaschedulestudent.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.zupacademy.praticaschedulestudent.R
import com.zupacademy.praticaschedulestudent.model.Student
import com.zupacademy.praticaschedulestudent.shared.titleUppercase
import com.zupacademy.praticaschedulestudent.shared.toast
import com.zupacademy.praticaschedulestudent.shared.value
import com.zupacademy.praticaschedulestudent.ui.activity.util.ConstForActivites.*
import com.zupacademy.praticaschedulestudent.ui.activity.util.FilterOptionMenu

class EditFormStudent : AppCompatActivity() {

    private lateinit var nameField: EditText
    private lateinit var phoneField: EditText
    private lateinit var emailField: EditText
    private lateinit var student: Student

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_student)
        title = TITLE_APPBAR_EDIT_FORM.value()
        initializeAttributes()
        verifyIntent()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_form_student_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        student.run {
            name = nameField.value()
            phone = phoneField.value()
            email = emailField.value()
            FilterOptionMenu
                .valueOf(item.titleUppercase())
                .action(this)
        }
        toast(this, EDITED_STUDENT.value())
        finish()
        return super.onOptionsItemSelected(item)
    }

    private fun initializeAttributes() {
        nameField = findViewById(R.id.activity_form_student_name)
        phoneField = findViewById(R.id.activity_form_student_phone)
        emailField = findViewById(R.id.activity_form_student_email)
    }

    private fun verifyIntent() {
        if(intent.hasExtra(EXTRA_INTENT_EDIT_STUDENT.value())) {
            student = intent.getSerializableExtra(EXTRA_INTENT_EDIT_STUDENT.value()) as Student
            student.run {
                nameField.setText(name)
                phoneField.setText(phone)
                emailField.setText(email)
            }
        }
    }
}