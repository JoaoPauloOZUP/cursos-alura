package com.zupacademy.scheduleofstudent.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import com.zupacademy.scheduleofstudent.database.entity.Student
import com.zupacademy.scheduleofstudent.database.repository.StudentRepository
import com.zupacademy.scheduleofstudent.databinding.FragmentStudentListBinding
import com.zupacademy.scheduleofstudent.retrofit.service.dto.StudentRequest
import com.zupacademy.scheduleofstudent.ui.adapter.StudentRecyclerAdapter
import com.zupacademy.scheduleofstudent.ui.helper.StudentItemTouchCallback
import com.zupacademy.scheduleofstudent.ui.listerner.OnItemClickListener
import com.zupacademy.scheduleofstudent.ui.viewmodel.StudentListViewModel
import com.zupacademy.scheduleofstudent.ui.viewmodel.factory.StudentListViewModelFactory
import org.koin.android.ext.android.inject

class StudentListFragment : Fragment() {

    private val listenerActivity: Listener by lazy {
         activity as Listener
    }

    private val itemTouchHelper: ItemTouchHelper by lazy {
        ItemTouchHelper(StudentItemTouchCallback(adapter))
    }

    private val viewModel by lazy {
        val provider: ViewModelProvider = ViewModelProviders.of(this,  factory)
        provider.get(StudentListViewModel::class.java)
    }

    private var _binding: FragmentStudentListBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: StudentRecyclerAdapter
    private val repository: StudentRepository by inject()
    private val factory: StudentListViewModelFactory by inject()

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
            listenerActivity.startFormStudentActivity { studentRequest ->
                viewModel.saveStudent(studentRequest, ::toast).observe(viewLifecycleOwner, { student ->
                    adapter.save(student)
                })
            }
        }
    }

    private fun configureOnItemClickListener() {
        val onItemClickListener = object : OnItemClickListener<Student>(){
            override fun onClick(item: Student, position: Int) {
                listenerActivity.startFormStudentActivityWithExtra(item) { student ->
                    viewModel.editStudent(student, ::toast).observe(viewLifecycleOwner, { student
                        adapter.edit(student)
                    })
                }
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

interface Listener {
    fun startFormStudentActivity(callbackNewStudentFragment: (student: StudentRequest) -> Unit)
    fun startFormStudentActivityWithExtra(extra: Student, callbackEditStudentFragment: (student: Student) -> Unit)
}