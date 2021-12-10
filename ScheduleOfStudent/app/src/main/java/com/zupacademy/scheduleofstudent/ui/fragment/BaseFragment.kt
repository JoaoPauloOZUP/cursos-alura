package com.zupacademy.scheduleofstudent.ui.fragment

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.zupacademy.scheduleofstudent.NavGraphDirections
import com.zupacademy.scheduleofstudent.R
import com.zupacademy.scheduleofstudent.database.repository.LoginRepository
import com.zupacademy.scheduleofstudent.ui.viewmodel.LoginViewModel
import org.koin.android.ext.android.inject

abstract class BaseFragment : Fragment() {

    private val navigationController: NavController by lazy {
        findNavController()
    }

    private val loginRepository: LoginRepository by inject()
    private val loginViewModel: LoginViewModel by lazy {
        LoginViewModel(loginRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.student_logout_application, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.title == "Logout") {
            loginViewModel.logout()
            val direction = NavGraphDirections.actionGlobalStudentLogin()
            navigationController.navigate(direction)
        }

        return super.onOptionsItemSelected(item)
    }
}