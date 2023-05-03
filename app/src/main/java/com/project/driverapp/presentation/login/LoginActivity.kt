package com.project.driverapp.presentation.login

import android.view.View
import com.project.driverapp.databinding.ActivityLoginBinding
import com.project.driverapp.presentation.base.BaseActivity
import com.project.driverapp.utils.goToHome
import com.project.driverapp.utils.goToRegister
import com.project.driverapp.utils.setEditTextAndButtonListener
import com.project.driverapp.utils.showResultDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity() {

    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding!!
    private val loginViewModel: LoginViewModel by viewModel()

    override fun setLayout(): View {
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initObserver() {
        super.initBaseObserver(loginViewModel)
        loginViewModel.loginSuccessLiveData.observe(this) {
            if (it) {
                goToHome()
            } else {
                showResultDialog(false)
            }
        }
    }

    override fun initClickListener() {
        with(binding) {
            tvEnabled.setOnClickListener {
                loginViewModel.login(
                    nomorHP = etNoHP.text.toString(),
                    password = etSandi.text.toString()
                )
            }
            tvBack.setOnClickListener {
                onBackPressed()
            }
            tvDaftar.setOnClickListener {
                goToRegister()
            }
        }
    }

    override fun initEditTextListener() {
        with(binding) {
            setEditTextAndButtonListener(
                listOf(
                    etNoHP,
                    etSandi,
                ), tvEnabled, tvDisabled
            )
        }
    }

}