package com.example.agenda.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.agenda.R
import com.example.agenda.dao.StudentDAO
import com.example.agenda.model.Student
import com.example.agenda.ui.util.ConstSharedActivities
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListStudentsActivity : AppCompatActivity() {

    companion object {
        private const val TITLE_APPBAR = "Students"
        private const val LYFECICLE = "LYFECICLE"
        private const val MENU_REMOVE = "Remove"
    }

    private val studentDAO = StudentDAO()
    private lateinit var listStudent: List<Student>
    private lateinit var listView: ListView
    private lateinit var fabNewStudent: FloatingActionButton
    private lateinit var arrayAdapterStudent: ArrayAdapter<Student>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_students)
        title = TITLE_APPBAR
        initializeAttributesViews()
        configureFabNewStudent()
        configureListStudents()

        Log.i(LYFECICLE, "onCreate")
    }

    override fun onCreateContextMenu(menu: ContextMenu, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.activity_list_student_menu, menu)
    }

    // Para cada menu de contexto for clicada sera chamado este evento
    override fun onContextItemSelected(item: MenuItem): Boolean {
        val menu = item.menuInfo as AdapterView.AdapterContextMenuInfo
        arrayAdapterStudent.getItem(menu.position).let { studentSelected ->
            removeStudentOnView(studentSelected as Student)
        }
        return super.onContextItemSelected(item)
    }

    override fun onStart() {
        super.onStart()
        Log.i(LYFECICLE, "onStart")
    }

    override fun onResume() {
        super.onResume()
        updateStudents()

        Log.i(LYFECICLE, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i(LYFECICLE, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i(LYFECICLE, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(LYFECICLE, "onDestroy")
    }

    private fun initializeAttributesViews() {
        listView = findViewById(R.id.activity_list_students_listview)
        fabNewStudent = findViewById(R.id.activity_list_students_fab_new_student)
    }

    private fun configureFabNewStudent() {
        fabNewStudent.setOnClickListener {
            startFormStudentActivity()
        }
    }

    private fun startFormStudentActivity() {
        startActivity(Intent(this, FormCreateStudentActivity::class.java))
    }

    private fun startFormStudentActivityWithExtra(extra: Student) {
        startActivity(
            Intent(this, FormEditStudentActivity::class.java)
                .putExtra(ConstSharedActivities.EXTRA_STUDENT, extra)
        )
    }

    private fun initializeListStudents() {
        listStudent = studentDAO.allStudents()
    }

    private fun configureListStudents() {
        arrayAdapterStudent = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1
        )
        listView.adapter = arrayAdapterStudent
        configureOnItemClick()
        configureOnItemLongClick()
        registerForContextMenu(listView)
    }

    private fun configureOnItemClick() {
        listView.setOnItemClickListener { parent, view, position, id ->
            parent.getItemAtPosition(position).let { student ->
                startFormStudentActivityWithExtra(student as Student)
            }
        }
    }

    private fun configureOnItemLongClick() {
        listView.setOnItemLongClickListener { parent, view, position, id ->
//            parent.getItemAtPosition(position).let { student ->
//                removeStudentOnView(student as Student)
//                return@let false
//            }
            return@setOnItemLongClickListener false
        }
    }

    private fun updateStudents() {
        initializeListStudents()
        arrayAdapterStudent.clear()
        arrayAdapterStudent.addAll(listStudent)
    }

    private fun removeStudentOnView(student: Student) {
        studentDAO.remove(student)
        arrayAdapterStudent.remove(student)
        arrayAdapterStudent.notifyDataSetChanged()
    }
}