package com.project.driverapp.domain.usecases.auth

import com.project.driverapp.data.utils.Resource
import com.project.driverapp.domain.model.User
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

interface AuthUseCase {

    fun addUser(user: User): Flow<Resource<Boolean>>
    fun register(email: String, password: String): Flow<Resource<AuthResult>>
    fun login(email: String, password: String): Flow<Resource<AuthResult>>
    fun getUserWithIDUser(idUser: String): Flow<Resource<User>>

}