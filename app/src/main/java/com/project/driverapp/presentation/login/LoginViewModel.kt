package com.project.driverapp.presentation.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.project.driverapp.data.pref.ProfilePrefs
import com.project.driverapp.data.utils.Resource
import com.project.driverapp.domain.model.User
import com.project.driverapp.domain.usecases.auth.AuthUseCase
import com.project.driverapp.presentation.base.BaseViewModel
import com.project.driverapp.utils.Constants
import com.project.driverapp.utils.toEmail
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authUseCase: AuthUseCase
) : BaseViewModel() {

    val loginSuccessLiveData = MutableLiveData<Boolean>()

    fun login(nomorHP: String, password: String) {
        loginSuccessLiveData.value = true
//        viewModelScope.launch {
//            authUseCase.login(nomorHP.toEmail(), password).collect {
//                when (it) {
//                    is Resource.Loading -> {
//                        showLoadingLiveData.value = true
//                    }
//                    is Resource.Success -> {
//                        getData()
//                    }
//                    is Resource.Error -> {
//                        showLoadingLiveData.value = false
//                        println("ERROR " + it.message)
//                    }
//                }
//            }
//        }
    }

    private fun getData() {
        viewModelScope.launch {
            val idUser = FirebaseAuth.getInstance().currentUser?.uid ?: ""
            authUseCase.getUserWithIDUser(idUser).collect {
                when (it) {
                    is Resource.Loading -> {
                        showLoadingLiveData.value = true
                    }
                    is Resource.Success -> {
                        showLoadingLiveData.value = false
                        val user = it.data ?: User()
                        ProfilePrefs.apply {
                            idFirebase = idUser
                            ProfilePrefs.idUser = user.idUser
                            email = user.email
                            fullname = user.fullname
                            jenisKelamin = user.jenisKelamin
                            pekerjaan = user.pekerjaan
                            namaUsaha = user.namaUsaha
                            alamat = user.alamat
                            noHP = user.noHP
                            tahunPengalaman = user.tahunPengalaman
                            jenisProdukYangDijual = user.jenisProdukYangDijual
                            role = Constants.PETANI
                        }
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
