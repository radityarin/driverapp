package com.ftp.keberlanjutanumkmbsc.presentation.home.users.ui.history

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ftp.keberlanjutanumkmbsc.data.logic.Kuesioner
import com.ftp.keberlanjutanumkmbsc.data.pref.ProfilePrefs
import com.ftp.keberlanjutanumkmbsc.data.utils.Resource
import com.ftp.keberlanjutanumkmbsc.domain.usecases.questioner.QuestionerUseCase
import com.ftp.keberlanjutanumkmbsc.presentation.base.BaseViewModel
import com.ftp.keberlanjutanumkmbsc.utils.UtilsDate.ISOTimeToDateTime
import kotlinx.coroutines.launch

class HistoryViewModel(
    private val questionerUseCase: QuestionerUseCase
) : BaseViewModel() {

    val listKuesionerLiveData = MutableLiveData<List<Kuesioner>>()

    fun getRiwayatKuesioner() {
        viewModelScope.launch {
            questionerUseCase.getAllQuestioner().collect { it ->
                when (it) {
                    is Resource.Loading -> {
                        showLoadingLiveData.value = true
                    }
                    is Resource.Success -> {
                        showLoadingLiveData.value = false
                        val listKuesioner = it.data ?: emptyList()
                        listKuesionerLiveData.value = listKuesioner.sortedBy { kuesioner -> kuesioner.urutan }
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