package com.zupacademy.praticaschedulestudent.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.zupacademy.praticaschedulestudent.R
import com.zupacademy.praticaschedulestudent.model.Student
import com.zupacademy.praticaschedulestudent.repository.StudentRepository
import com.zupacademy.praticaschedulestudent.shared.titleUppercase
import com.zupacademy.praticaschedulestudent.ui.activity.adapter.StudentCustomAdapter
import com.zupacademy.praticaschedulestudent.ui.activity.util.ConstForActivites
import com.zupacademy.praticaschedulestudent.ui.activity.util.ConstForActivites.*
import com.zupacademy.praticaschedulestudent.ui.activity.util.FilterContextMenu

class ListStudentsActivity : AppCompatActivity() {

    private lateinit var listViewStudent: ListView
    private lateinit var fabAddStudent: FloatingActionButton
    private lateinit var repository: StudentRepository
    private lateinit var listStudents: List<Student>
    private lateinit var adapter: StudentCustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_student)
        title = TITLE_APPBAR_LIST_STUDENT.value()
        initializeAttributes()
        configureFabOnClick()
        configureOnItemClick()
    }

    override fun onResume() {
        super.onResume()
        updateStudents()
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.activity_remove_student_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val menu = item.menuInfo as AdapterView.AdapterContextMenuInfo
        adapter.getItem(menu.position).let { studentSelected ->
            FilterContextMenu
                .valueOf(item.titleUppercase())
                .action(this, adapter, studentSelected as Student)
        }
        return super.onContextItemSelected(item)
    }

    private fun initializeAttributes() {
        listViewStudent = findViewById(R.id.activity_list_student_listview)
        registerForContextMenu(listViewStudent)
        fabAddStudent = findViewById(R.id.activity_list_student_fab)
        repository = StudentRepository()
        listStudents = repository.allStudents()
        adapter = StudentCustomAdapter(lifecycle, listStudents.toMutableList(), this)
        listViewStudent.adapter = adapter
    }

    private fun configureFabOnClick() {
        fabAddStudent.setOnClickListener {
           startCreateFormActivity()
        }
    }

    private fun configureOnItemClick() {
        listViewStudent.setOnItemClickListener { parent, view, position, id ->
           parent.getItemAtPosition(position).let { student ->
               startEditFormActivity(student as Student)
           }
        }
    }

    private fun startCreateFormActivity() {
        startActivity(
            Intent(this, CreateFormStudent::class.java)
        )
    }

    private fun startEditFormActivity(student: Student) {
        startActivity(
            Intent(this, EditFormStudent::class.java)
                .putExtra(EXTRA_INTENT_EDIT_STUDENT.value(), student)
        )
    }

    private fun updateStudents() {
        listStudents = repository.allStudents()
        adapter.addAll(listStudents)
    }
}