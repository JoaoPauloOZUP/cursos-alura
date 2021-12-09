package com.zupacademy.scheduleofstudent.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.zupacademy.scheduleofstudent.R
import com.zupacademy.scheduleofstudent.databinding.FragmentStudentLoginBinding

class StudentLoginFragment : Fragment() {

    private var _binding: FragmentStudentLoginBinding? = null
    private val binding get() = _binding!!

    private val navigationController: NavController by lazy {
        findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStudentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.stusentLoginBtn.setOnClickListener {
            navigationController.navigate(R.id.action_StudentLogin_to_StudentList)
        }
    }
}