package com.project.driverapp.presentation.home.users.ui.history

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.project.driverapp.data.logic.Kuesioner
import com.project.driverapp.data.utils.Resource
import com.project.driverapp.domain.usecases.questioner.QuestionerUseCase
import com.project.driverapp.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

class HistoryViewModel(
    private val questionerUseCase: QuestionerUseCase
) : BaseViewModel() {

    val listKuesionerLiveData = MutableLiveData<List<Kuesioner>>()

    fun getRiwayatKuesioner() {
        viewModelScope.launch {
//            questionerUseCase.getAllQuestioner().collect { it ->
//                when (it) {
//                    is Resource.Loading -> {
//                        showLoadingLiveData.value = true
//                    }
//                    is Resource.Success -> {
//                        showLoadingLiveData.value = false
//                        val listKuesioner = it.data ?: emptyList()
//                        listKuesionerLiveData.value = listKuesioner.sortedBy { kuesioner -> kuesioner.urutan }
//                    }
//                    is Resource.Error -> {
//                        showLoadingLiveData.value = false
//                        println("ERROR " + it.message)
//                    }
//                }
//            }
        }
    }
}