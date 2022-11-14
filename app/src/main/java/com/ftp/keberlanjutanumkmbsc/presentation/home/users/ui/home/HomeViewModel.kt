package com.ftp.keberlanjutanumkmbsc.presentation.home.users.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ftp.keberlanjutanumkmbsc.data.pref.ProfilePrefs

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Hi, " + ProfilePrefs.fullname.split(" ").first()
    }
    val text: LiveData<String> = _text
}