package com.zupacademy.scheduleofstudent.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.zupacademy.scheduleofstudent.R
import com.zupacademy.scheduleofstudent.database.repository.LoginRepository
import com.zupacademy.scheduleofstudent.databinding.FragmentStudentLoginBinding
import com.zupacademy.scheduleofstudent.ui.viewmodel.LoginViewModel
import org.koin.android.ext.android.inject

class StudentLoginFragment : Fragment() {

    private var _binding: FragmentStudentLoginBinding? = null
    private val binding get() = _binding!!

    private val loginRepository: LoginRepository by inject<LoginRepository>()

    private val loginViewModel: LoginViewModel by lazy {
        LoginViewModel(loginRepository)
    }

    private val navigationController: NavController by lazy {
        findNavController()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(loginViewModel.isLogged()) {
                navigationController.navigate(R.id.action_StudentLogin_to_StudentList)
        }
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
            loginViewModel.login()
            navigationController.navigate(R.id.action_StudentLogin_to_StudentList)
        }
    }
}