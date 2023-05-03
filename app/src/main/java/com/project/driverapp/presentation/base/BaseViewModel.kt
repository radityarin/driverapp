package com.project.driverapp.presentation.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.driverapp.data.utils.Resource

open class BaseViewModel : ViewModel() {

    val showLoadingLiveData = MutableLiveData<Boolean>()
    val showDialogLiveData = MutableLiveData<Resource<Any>>()
    val showToastLiveData = MutableLiveData<Resource<Any>>()

}