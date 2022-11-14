package com.ftp.keberlanjutanumkmbsc.domain.usecases.questioner

import com.ftp.keberlanjutanumkmbsc.data.logic.Kuesioner
import com.ftp.keberlanjutanumkmbsc.data.utils.Resource
import com.ftp.keberlanjutanumkmbsc.domain.repositories.AppRepository
import kotlinx.coroutines.flow.Flow

class QuestionerInteractor(private val repository: AppRepository) : QuestionerUseCase {

    override fun addQuestioner(questioner: Kuesioner): Flow<Resource<Boolean>> =
        repository.addQuestioner(questioner)

    override fun getAllQuestioner(): Flow<Resource<List<Kuesioner>>> =
        repository.getAllQuestioner()

    override fun getListQuestionerWithSpecificID(idUser: String): Flow<Resource<List<Kuesioner>>> =
        repository.getListQuestionerWithSpecificID(idUser)
}