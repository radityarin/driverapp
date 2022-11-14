package com.ftp.keberlanjutanumkmbsc.presentation.home.users.ui.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ftp.keberlanjutanumkmbsc.data.pref.ProfilePrefs
import com.ftp.keberlanjutanumkmbsc.data.utils.Resource
import com.ftp.keberlanjutanumkmbsc.domain.model.User
import com.ftp.keberlanjutanumkmbsc.domain.usecases.auth.AuthUseCase
import com.ftp.keberlanjutanumkmbsc.presentation.base.BaseViewModel
import com.ftp.keberlanjutanumkmbsc.utils.Constants
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