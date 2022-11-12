package com.ftp.keberlanjutanumkmbsc.domain.usecases.auth

import com.ftp.keberlanjutanumkmbsc.data.utils.Resource
import com.ftp.keberlanjutanumkmbsc.domain.model.User
import com.ftp.keberlanjutanumkmbsc.domain.repositories.AppRepository
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

class AuthInteractor(private val repository: AppRepository) : AuthUseCase {

    override fun login(email: String, password: String): Flow<Resource<AuthResult>> =
        repository.loginUser(email, password)

    override fun getUserWithIDUser(idUser: String): Flow<Resource<User>> =
        repository.getUserWithIDUser(idUser)

    override fun register(email: String, password: String): Flow<Resource<AuthResult>> =
        repository.registerUser(email, password)

    override fun addUser(user: User): Flow<Resource<Boolean>> =
        repository.addUsers(user)
}