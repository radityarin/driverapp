package com.project.driverapp.domain.repositories

import com.project.driverapp.data.logic.Kuesioner
import com.project.driverapp.domain.model.User
import com.project.driverapp.data.utils.Resource
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

interface AppRepository {

    fun registerUser(email: String, password: String): Flow<Resource<AuthResult>>
    fun loginUser(email: String, password: String): Flow<Resource<AuthResult>>
    fun addUsers(user: User): Flow<Resource<Boolean>>
    fun getUserWithIDUser(idUser: String): Flow<Resource<User>>

    fun addQuestioner(questioner: Kuesioner): Flow<Resource<Boolean>>
    fun getAllQuestioner(): Flow<Resource<List<Kuesioner>>>
    fun getListQuestionerWithSpecificID(idUser: String): Flow<Resource<List<Kuesioner>>>
}