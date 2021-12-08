package com.zupacademy.scheduleofstudent.ui.fragment

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.zupacademy.scheduleofstudent.R
import com.zupacademy.scheduleofstudent.database.entity.Student
import com.zupacademy.scheduleofstudent.databinding.FragmentStudentFormBinding
import com.zupacademy.scheduleofstudent.ui.shared.EXTRA_STUDENT
import com.zupacademy.scheduleofstudent.ui.shared.OPTION_MENU_SAVE
import com.zupacademy.scheduleofstudent.ui.viewmodel.StudentListViewModel
import com.zupacademy.scheduleofstudent.ui.viewmodel.factory.StudentListViewModelFactory
import org.koin.android.ext.android.inject

class StudentFormEditFragment : Fragment() {

    private var _binding: FragmentStudentFormBinding? = null
    private val binding get() = _binding!!

    private val student: Student by lazy {
        arguments?.getSerializable(EXTRA_STUDENT) as Student
    }

    private val navigationController: NavController by lazy {
        findNavController()
    }

    private val factory: StudentListViewModelFactory by inject()
    private val viewModel by lazy {
        val provider: ViewModelProvider = ViewModelProviders.of(this,  factory)
        provider.get(StudentListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentStudentFormBinding.inflate(inflater, container, false)
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
            viewModel.editStudent(student, ::toast)
            navigationController.navigate(R.id.action_StudentEdit_to_StudentList)
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

    private fun toast() {
        Toast.makeText(requireContext(), "Error your network", Toast.LENGTH_SHORT).show()
    }
}