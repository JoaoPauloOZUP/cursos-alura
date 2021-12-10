package com.zupacademy.scheduleofstudent.ui.fragment

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import com.zupacademy.scheduleofstudent.R
import com.zupacademy.scheduleofstudent.database.entity.Student
import com.zupacademy.scheduleofstudent.database.repository.LoginRepository
import com.zupacademy.scheduleofstudent.database.repository.StudentRepository
import com.zupacademy.scheduleofstudent.databinding.FragmentStudentListBinding
import com.zupacademy.scheduleofstudent.ui.adapter.StudentRecyclerAdapter
import com.zupacademy.scheduleofstudent.ui.helper.StudentItemTouchCallback
import com.zupacademy.scheduleofstudent.ui.listerner.OnItemClickListener
import com.zupacademy.scheduleofstudent.ui.shared.EXTRA_STUDENT
import com.zupacademy.scheduleofstudent.ui.viewmodel.LoginViewModel
import com.zupacademy.scheduleofstudent.ui.viewmodel.StudentListViewModel
import com.zupacademy.scheduleofstudent.ui.viewmodel.factory.StudentListViewModelFactory
import org.koin.android.ext.android.inject

class StudentListFragment : BaseFragment() {

    private val itemTouchHelper: ItemTouchHelper by lazy {
        ItemTouchHelper(StudentItemTouchCallback(adapter))
    }

    private var _binding: FragmentStudentListBinding? = null
    private val binding get() = _binding!!

    private val navigationController: NavController by lazy {
        findNavController()
    }

    private val loginRepository: LoginRepository by inject()
    private val loginViewModel: LoginViewModel by lazy {
        LoginViewModel(loginRepository)
    }

    private val factory: StudentListViewModelFactory by inject()
    private val viewModel by lazy {
        val provider: ViewModelProvider = ViewModelProviders.of(this,  factory)
        provider.get(StudentListViewModel::class.java)
    }

    private lateinit var adapter: StudentRecyclerAdapter
    private val repository: StudentRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(loginViewModel.isNotLogged()) {
            navigationController.navigate(R.id.action_global_StudentLogin)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentStudentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureFabOnclickListener()
        configureAdapter()
    }

    private fun configureAdapter() {
        viewModel.findAllStudents(::toast).observe(viewLifecycleOwner, { studentList ->
            adapter = StudentRecyclerAdapter(requireContext(), studentList.toMutableList(), repository)
            configureOnItemClickListener()
            binding.studentListRecyclerView.adapter = adapter
            configureItemTouchHelper()
        })
    }

    private fun configureFabOnclickListener() {
         binding.studentListNewStudentFab.setOnClickListener {
            navigationController.navigate(R.id.action_StudentList_to_StudentCreate)
        }
    }

    private fun configureOnItemClickListener() {
        val onItemClickListener = object : OnItemClickListener<Student>(){
            override fun onClick(student: Student, position: Int) {
                val directions = StudentListFragmentDirections.actionStudentListToStudentEdit(student)
                navigationController.navigate(directions)
            }
        }
        adapter.setOnItemClickListener(onItemClickListener)
    }

    private fun configureItemTouchHelper() {
        itemTouchHelper.attachToRecyclerView(binding.studentListRecyclerView)
    }

    private fun toast() {
        Toast.makeText(requireContext(), "Error your network", Toast.LENGTH_SHORT).show()
    }
}