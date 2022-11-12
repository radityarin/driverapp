package com.ftp.keberlanjutanumkmbsc.presentation.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ftp.keberlanjutanumkmbsc.data.utils.Resource
import com.ftp.keberlanjutanumkmbsc.domain.usecases.auth.AuthUseCase
import com.ftp.keberlanjutanumkmbsc.presentation.base.BaseViewModel
import com.ftp.keberlanjutanumkmbsc.utils.toEmail
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authUseCase: AuthUseCase
) : BaseViewModel() {

    val loginSuccessLiveData = MutableLiveData<Boolean>()

    fun login(nomorHP: String, password: String) {
        viewModelScope.launch {
            authUseCase.login(nomorHP.toEmail(), password).collect {
                when (it) {
                    is Resource.Loading -> {
                        showLoadingLiveData.value = true
                    }
                    is Resource.Success -> {
                        showLoadingLiveData.value = false
                        loginSuccessLiveData.value = true
                    }
                    is Resource.Error -> {
                        showLoadingLiveData.value = false
                        println("ERROR " + it.message)
                    }
                }
            }
        }
    }

}
