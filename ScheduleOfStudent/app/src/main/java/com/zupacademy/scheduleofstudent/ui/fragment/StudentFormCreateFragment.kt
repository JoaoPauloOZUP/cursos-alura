package com.zupacademy.scheduleofstudent.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.zupacademy.scheduleofstudent.R
import com.zupacademy.scheduleofstudent.database.entity.Student
import com.zupacademy.scheduleofstudent.databinding.ActivityFormCreateStudentBinding
import com.zupacademy.scheduleofstudent.retrofit.service.dto.StudentRequest
import com.zupacademy.scheduleofstudent.ui.shared.EXTRA_STUDENT
import com.zupacademy.scheduleofstudent.ui.shared.OPTION_MENU_SAVE

class StudentFormCreateFragment : Fragment() {

    private var _binding: ActivityFormCreateStudentBinding? = null
    private val binding get() = _binding!!

    private val studentCreateExtra: StudentCreateExtra by lazy {
        activity as StudentCreateExtra
    }

    private val student: Student by lazy {
        arguments?.getSerializable(EXTRA_STUDENT) as Student
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = ActivityFormCreateStudentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.activity_student_form_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemSelected = item.title as String
        if(itemSelected == OPTION_MENU_SAVE) {
            val studentRequest = createdStudent()
            studentCreateExtra.extraValueForCreate(studentRequest)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun createdStudent(): StudentRequest {
        return StudentRequest(
            name = binding.formStudentsName.text.toString(),
            phone = binding.formStudentsPhone.text.toString(),
            email = binding.formStudentsEmail.text.toString()
        )
    }
}

interface StudentCreateExtra {
    fun extraValueForCreate(studentRequest: StudentRequest)
}