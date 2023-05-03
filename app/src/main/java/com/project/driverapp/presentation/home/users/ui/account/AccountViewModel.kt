package com.project.driverapp.presentation.home.users.ui.account

import androidx.lifecycle.viewModelScope
import com.project.driverapp.data.pref.ProfilePrefs
import com.project.driverapp.data.utils.Resource
import com.project.driverapp.domain.model.User
import com.project.driverapp.domain.usecases.auth.AuthUseCase
import com.project.driverapp.presentation.base.BaseViewModel
import com.project.driverapp.utils.Constants
import kotlinx.coroutines.launch

class AccountViewModel(
    private val authUseCase: AuthUseCase
) : BaseViewModel() {

    fun editData(user: User) {
        viewModelScope.launch {
            authUseCase.addUser(user).collect {
                when (it) {
                    is Resource.Loading -> {
                        showLoadingLiveData.value = true
                    }
                    is Resource.Success -> {
                        showLoadingLiveData.value = false
                        ProfilePrefs.apply {
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
                    }
                    is Resource.Error -> {
                        showLoadingLiveData.value = false
                    }
                }
            }
        }
    }
}