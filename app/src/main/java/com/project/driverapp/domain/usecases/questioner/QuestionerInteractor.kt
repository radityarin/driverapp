package com.project.driverapp.domain.usecases.questioner

import com.project.driverapp.data.logic.Kuesioner
import com.project.driverapp.data.utils.Resource
import com.project.driverapp.domain.repositories.AppRepository
import kotlinx.coroutines.flow.Flow

class QuestionerInteractor(private val repository: AppRepository) : QuestionerUseCase {

    override fun addQuestioner(questioner: Kuesioner): Flow<Resource<Boolean>> =
        repository.addQuestioner(questioner)

    override fun getAllQuestioner(): Flow<Resource<List<Kuesioner>>> =
        repository.getAllQuestioner()

    override fun getListQuestionerWithSpecificID(idUser: String): Flow<Resource<List<Kuesioner>>> =
        repository.getListQuestionerWithSpecificID(idUser)
}