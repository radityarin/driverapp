package com.project.driverapp.presentation.home.users.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.driverapp.data.pref.ProfilePrefs

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Hi, " + ProfilePrefs.fullname.split(" ").first()
    }
    val text: LiveData<String> = _text
}