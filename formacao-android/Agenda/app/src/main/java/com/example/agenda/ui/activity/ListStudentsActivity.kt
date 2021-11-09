package com.example.agenda.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.agenda.R
import com.example.agenda.database.Database
import com.example.agenda.database.room_dao.RoomStudentDAO
import com.example.agenda.model.Student
import com.example.agenda.ui.activity.adapter.StudentRecyclerAdapter
import com.example.agenda.ui.activity.adapter.helper.StudentItemTouchCallback
import com.example.agenda.ui.activity.adapter.listener.OnItemClickListener
import com.example.agenda.ui.activity.util.ConstSharedActivities.Companion.CREATED_STUDENT_REQUEST
import com.example.agenda.ui.activity.util.ConstSharedActivities.Companion.EDITED_STUDENT_REQUEST
import com.example.agenda.ui.activity.util.ConstSharedActivities.Companion.EXTRA_STUDENT
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListStudentsActivity : AppCompatActivity() {

    companion object {
        private const val TITLE_APPBAR = "Students"
    }

    private lateinit var dao: RoomStudentDAO
    private lateinit var listStudent: List<Student>
    private lateinit var recyclerView: RecyclerView
    private lateinit var itemTouchHelper: ItemTouchHelper
    private lateinit var fabNewStudent: FloatingActionButton
    private lateinit var adapter: StudentRecyclerAdapter
    private lateinit var someActivityResult: ActivityResultLauncher<Intent>
    private var positionStudentInAdapter: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_students)
        title = TITLE_APPBAR
        configureDao()
        configureActivityResult()
        initializeAttributesViews()
        configureFabNewStudent()
        configureRecyclerView()
    }

    override fun onResume() {
        super.onResume()
    }

    private fun configureOnItemClickListener() {
        adapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(student: Student, position: Int) {
                positionStudentInAdapter = position
                startFormStudentActivityWithExtra(student)
            }
        })
    }

    private fun initializeAttributesViews() {
        recyclerView = findViewById(R.id.activity_list_students_listview)
        fabNewStudent = findViewById(R.id.activity_list_students_fab_new_student)
    }

    private fun configureActivityResult() {
        someActivityResult = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            activityResult(it.resultCode, it.data)
        }
    }

    private fun activityResult(resultCode: Int, data: Intent?) {
        if (resultCode == CREATED_STUDENT_REQUEST) {
            val extra = data?.getSerializableExtra(EXTRA_STUDENT) as Student
            saveStudent(extra)
        }

        if (resultCode == EDITED_STUDENT_REQUEST) {
            val extra = data?.getSerializableExtra(EXTRA_STUDENT) as Student
            editStudent(extra)
        }
    }

    private fun saveStudent(student: Student) {
        dao.save(student)
        adapter.save(student)
    }

    private fun editStudent(student: Student) {
        dao.edit(student)
        positionStudentInAdapter?.let { positionStudentInAdapter ->
            adapter.edit(student, positionStudentInAdapter)
        }
        positionStudentInAdapter = null
    }

    private fun configureFabNewStudent() {
        fabNewStudent.setOnClickListener {
            startFormStudentActivity()
        }
    }

    private fun startFormStudentActivity() {
        someActivityResult.launch(
            Intent(this, FormCreateStudentActivity::class.java)
        )
    }

    private fun startFormStudentActivityWithExtra(extra: Student) {
        someActivityResult.launch(
            Intent(this, FormEditStudentActivity::class.java)
                .putExtra(EXTRA_STUDENT, extra)
        )
    }

    private fun initializeListStudents() {
        listStudent = dao.allStudents()
    }

    private fun configureRecyclerView() {
        initializeListStudents()
        adapter = StudentRecyclerAdapter(this, listStudent.toMutableList(), lifecycle)
        recyclerView.adapter = adapter
        configureItemTouchHelper()
        configureOnItemClickListener()
    }

    private fun configureItemTouchHelper() {
        itemTouchHelper = ItemTouchHelper(StudentItemTouchCallback(adapter, dao))
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun configureDao() {
        val instance = Database.getInstance(this)
        dao = instance.getRoomStudentDAO()
    }
}