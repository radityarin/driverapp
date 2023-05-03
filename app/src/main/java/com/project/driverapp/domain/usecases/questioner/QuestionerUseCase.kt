package com.project.driverapp.domain.usecases.questioner

import com.project.driverapp.data.logic.Kuesioner
import com.project.driverapp.data.utils.Resource
import kotlinx.coroutines.flow.Flow

interface QuestionerUseCase {

    fun addQuestioner(questioner: Kuesioner): Flow<Resource<Boolean>>
    fun getAllQuestioner(): Flow<Resource<List<Kuesioner>>>
    fun getListQuestionerWithSpecificID(idUser: String): Flow<Resource<List<Kuesioner>>>

}