package com.ftp.keberlanjutanumkmbsc.domain.usecases.questioner

import com.ftp.keberlanjutanumkmbsc.data.logic.Kuesioner
import com.ftp.keberlanjutanumkmbsc.data.utils.Resource
import com.ftp.keberlanjutanumkmbsc.domain.model.User
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

interface QuestionerUseCase {

    fun addQuestioner(questioner: Kuesioner): Flow<Resource<Boolean>>
    fun getAllQuestioner(): Flow<Resource<List<Kuesioner>>>
    fun getListQuestionerWithSpecificID(idUser: String): Flow<Resource<List<Kuesioner>>>

}