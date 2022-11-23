package com.ftp.keberlanjutanumkmbsc.presentation.login

import android.view.View
import com.ftp.keberlanjutanumkmbsc.databinding.ActivityLoginBinding
import com.ftp.keberlanjutanumkmbsc.presentation.base.BaseActivity
import com.ftp.keberlanjutanumkmbsc.utils.*
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
                if (etNoHP.text.toString() == "admin6@system.com" && etSandi.text.toString() == "Pass123!"){
                    goToHomeAdmin()
                } else {
                    loginViewModel.login(
                        nomorHP = etNoHP.text.toString(),
                        password = etSandi.text.toString()
                    )
                }
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