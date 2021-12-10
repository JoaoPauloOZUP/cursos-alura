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
import com.zupacademy.scheduleofstudent.database.repository.LoginRepository
import com.zupacademy.scheduleofstudent.databinding.FragmentStudentFormBinding
import com.zupacademy.scheduleofstudent.retrofit.service.dto.StudentRequest
import com.zupacademy.scheduleofstudent.ui.shared.OPTION_MENU_SAVE
import com.zupacademy.scheduleofstudent.ui.viewmodel.LoginViewModel
import com.zupacademy.scheduleofstudent.ui.viewmodel.StudentListViewModel
import com.zupacademy.scheduleofstudent.ui.viewmodel.factory.StudentListViewModelFactory
import org.koin.android.ext.android.inject

class StudentFormCreateFragment : BaseFragment() {

    private var _binding: FragmentStudentFormBinding? = null
    private val binding get() = _binding!!

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
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentStudentFormBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.activity_student_form_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemSelected = item.title
        if(itemSelected == OPTION_MENU_SAVE) {
            val studentRequest = createdStudent()
            viewModel.saveStudent(studentRequest, ::toast)
            navigationController.popBackStack()
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

    private fun toast() {
        Toast.makeText(requireContext(), "Error your network", Toast.LENGTH_SHORT).show()
    }
}