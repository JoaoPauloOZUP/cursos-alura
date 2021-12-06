package com.zupacademy.scheduleofstudent.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
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

    private val factory: StudentListViewModelFactory by inject()
    private val viewModel by lazy {
        val provider: ViewModelProvider = ViewModelProviders.of(this,  factory)
        provider.get(StudentListViewModel::class.java)
    }

    private var _binding: FragmentStudentListBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: StudentRecyclerAdapter
    private val repository: StudentRepository by inject()


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
            listenerActivity.startFormStudentFragment { studentRequest ->
                viewModel.saveStudent(studentRequest, ::toast)
            }
        }
    }

    private fun configureOnItemClickListener() {
        val onItemClickListener = object : OnItemClickListener<Student>(){
            override fun onClick(item: Student, position: Int) {
                listenerActivity.startFormStudentFragmentWithExtra(item) { student ->
                    viewModel.editStudent(student, ::toast)
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

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i("DESTROY", "FRAGMENT DESTRUDI")
    }
}

interface Listener {
    fun startFormStudentFragment(callbackNewStudentFragment: (student: StudentRequest) -> Unit)
    fun startFormStudentFragmentWithExtra(extra: Student, callbackEditStudentFragment: (student: Student) -> Unit)
}