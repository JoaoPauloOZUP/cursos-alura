package com.zupacademy.scheduleofstudent.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.zupacademy.scheduleofstudent.R
import com.zupacademy.scheduleofstudent.database.entity.Student
import com.zupacademy.scheduleofstudent.retrofit.service.dto.StudentRequest
import com.zupacademy.scheduleofstudent.ui.fragment.Listener
import com.zupacademy.scheduleofstudent.ui.shared.CREATED_RESULT
import com.zupacademy.scheduleofstudent.ui.shared.EDITED_RESULT
import com.zupacademy.scheduleofstudent.ui.shared.EXTRA_STUDENT
import com.zupacademy.scheduleofstudent.ui.viewmodel.StudentListViewModel
import com.zupacademy.scheduleofstudent.ui.viewmodel.factory.StudentListViewModelFactory
import org.koin.android.ext.android.inject

class StudentList : AppCompatActivity(), Listener {

    companion object {
        private const val APPBAR_TITLE = "Student list"
    }

    private lateinit var callbackNewStudentFragment: (student: StudentRequest) -> Unit
    private lateinit var callbackEditStudentFragment: (student: Student) -> Unit
    private lateinit var someActivityResult: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list)
        title = APPBAR_TITLE
        configureActivityResult()
    }

    private fun configureActivityResult() {
        someActivityResult = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            activityResult(it.resultCode, it.data)
        }
    }

    private fun activityResult(resultCode: Int, data: Intent?) {
        if (resultCode ==  CREATED_RESULT) {
            val studentRequest = data?.getSerializableExtra(EXTRA_STUDENT) as StudentRequest
            callbackNewStudentFragment(studentRequest)
        }

        if (resultCode ==  EDITED_RESULT) {
            val studentEdit = data?.getSerializableExtra(EXTRA_STUDENT) as Student
            callbackEditStudentFragment(studentEdit)
        }
    }

    override fun startFormStudentActivity(callbackNewStudentFragment: (student: StudentRequest) -> Unit) {
        someActivityResult.launch(
            Intent(this, FormCreateStudent::class.java)
        )
        this.callbackNewStudentFragment = callbackNewStudentFragment
    }

    override fun startFormStudentActivityWithExtra(student: Student, callbackEditStudentFragment: (student: Student) -> Unit) {
        someActivityResult.launch(
            Intent(this, FormEditStudent::class.java)
                .putExtra(EXTRA_STUDENT, student)
        )
        this.callbackEditStudentFragment = callbackEditStudentFragment
    }
}