package com.zupacademy.scheduleofstudent.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.zupacademy.scheduleofstudent.R
import com.zupacademy.scheduleofstudent.database.entity.Student
import com.zupacademy.scheduleofstudent.databinding.ActivityFormCreateStudentBinding
import com.zupacademy.scheduleofstudent.ui.shared.EXTRA_STUDENT
import com.zupacademy.scheduleofstudent.ui.shared.OPTION_MENU_SAVE

class StudentFormEditFragment : Fragment() {

    private var _binding: ActivityFormCreateStudentBinding? = null
    private val binding get() = _binding!!

    private val studentEditExtra: StudentEditExtra by lazy {
        activity as StudentEditExtra
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.activity_student_form_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemSelected = item.title as String
        if(itemSelected == OPTION_MENU_SAVE) {
            studentEdit()
            studentEditExtra.extraValueForEdit(student)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun studentEdit() {
        student.name = binding.formStudentsName.text.toString()
        student.phone = binding.formStudentsPhone.text.toString()
        student.email = binding.formStudentsEmail.text.toString()
    }

    private fun bind() {
        binding.formStudentsName.setText(student.name)
        binding.formStudentsPhone.setText(student.phone)
        binding.formStudentsEmail.setText(student.email)
    }
}

interface StudentEditExtra {
    fun extraValueForEdit(student: Student)
}