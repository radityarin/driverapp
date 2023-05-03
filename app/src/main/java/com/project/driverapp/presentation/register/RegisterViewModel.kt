package com.project.driverapp.presentation.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.project.driverapp.data.pref.ProfilePrefs
import com.project.driverapp.data.utils.Resource
import com.project.driverapp.domain.model.User
import com.project.driverapp.domain.usecases.auth.AuthUseCase
import com.project.driverapp.presentation.base.BaseViewModel
import com.project.driverapp.utils.Constants
import com.project.driverapp.utils.toEmail
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val authUseCase: AuthUseCase
) : BaseViewModel() {

    val registerSuccessLiveData = MutableLiveData<Boolean>()
    var user: User = User()

    fun register(
        namaLengkap: String,
        jenisKelamin: String,
        pekerjaan: String,
        namaUsaha: String,
        alamat: String,
        nomorHP: String,
        pengalamanUsahaTahun: String,
        jenisProdukYangDijual: String,
        kataSandi: String,
        konfirmasiKataSandi: String,
    ) {
        if (kataSandi != konfirmasiKataSandi) {
            registerSuccessLiveData.value = false
        }
        user = User(
            email = nomorHP.toEmail(),
            fullname = namaLengkap,
            jenisKelamin = jenisKelamin,
            pekerjaan = pekerjaan,
            namaUsaha = namaUsaha,
            alamat = alamat,
            noHP = nomorHP,
            tahunPengalaman = pengalamanUsahaTahun,
            jenisProdukYangDijual = jenisProdukYangDijual,
        )
        viewModelScope.launch {
            authUseCase.register(nomorHP.toEmail(), kataSandi).collect {
                when (it) {
                    is Resource.Loading -> {
                        showLoadingLiveData.value = true
                    }
                    is Resource.Success -> {
                        addUser(it.data)
                    }
                    is Resource.Error -> {
                        showLoadingLiveData.value = false
                        println("ERROR " + it.message)
                    }
                }
            }
        }
    }

    private fun addUser(data: AuthResult?) {
        user.apply {
            idUser = data?.user?.uid.toString()
        }
        viewModelScope.launch {
            authUseCase.addUser(user).collect {
                when (it) {
                    is Resource.Loading -> {
                        showLoadingLiveData.value = true
                    }
                    is Resource.Success -> {
                        showLoadingLiveData.value = false
                        ProfilePrefs.apply {
                            idFirebase = data?.user?.uid.toString()
                            idUser = user.idUser
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
                        registerSuccessLiveData.value = true
                    }
                    is Resource.Error -> {
                        showLoadingLiveData.value = false
                    }
                }
            }
        }
    }


//    fun register(email: String, password: String) {
//        authUseCase.register(email, password).asLiveData()
//    }
//
//    fun addUser(user: User) = authUseCase.addUser(user).asLiveData()
//
//    fun getUserWithIDUser(idUser: String) = authUseCase.getUserWithIDUser(idUser).asLiveData()

}
