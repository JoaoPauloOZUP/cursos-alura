package com.example.agenda.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.agenda.R
import com.example.agenda.model.Student
import com.example.agenda.ui.activity.util.ConstSharedActivities.Companion.DURATION_TOAST
import com.example.agenda.ui.activity.util.ConstSharedActivities.Companion.EDITED_STUDENT_REQUEST
import com.example.agenda.ui.activity.util.ConstSharedActivities.Companion.EXTRA_STUDENT

class FormEditStudentActivity : AppCompatActivity() {

    companion object {
        private const val TITLE_APPBAR = "Edit student"
        private const val EDITED_STUDENT = "Edited student"
    }

    private lateinit var nameField: EditText
    private lateinit var phoneField: EditText
    private lateinit var phoneCelularField: EditText
    private lateinit var emailField: EditText
    private var student: Student?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_student)
        title = TITLE_APPBAR
        initializeAttributesViews()
        verifyIntent()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_form_student_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val titleOptionMenu = item.title as String
        if(titleOptionMenu == "Save") {
            setResult(
                EDITED_STUDENT_REQUEST,
                Intent(this, ListStudentsActivity::class.java)
                    .putExtra(EXTRA_STUDENT, editStudent())
            )
        }
        Toast.makeText(this, EDITED_STUDENT, DURATION_TOAST).show()
        finish()
        return super.onOptionsItemSelected(item)
    }

    private fun editStudent(): Student {
        return student!!.apply {
            name = nameField.text.toString()
            email = emailField.text.toString()
        }
    }

    private fun initializeAttributesViews() {
        nameField = findViewById(R.id.activity_form_students_name)
        phoneField = findViewById(R.id.activity_form_students_phone)
        phoneCelularField = findViewById(R.id.activity_form_students_celular_phone)
        emailField = findViewById(R.id.activity_form_students_email)
    }

    private fun intentIsNotEmpty(): Boolean {
        return intent.hasExtra(EXTRA_STUDENT)
    }

    private fun verifyIntent() {
        if(intentIsNotEmpty()) {
            student = intent.extras!!.getSerializable(EXTRA_STUDENT) as Student
            configureFormForEdit(student)
        }
    }

    private fun configureFormForEdit(student: Student?) {
        student!!.run {
            nameField.setText(name)
            emailField.setText(email)
        }
    }
}