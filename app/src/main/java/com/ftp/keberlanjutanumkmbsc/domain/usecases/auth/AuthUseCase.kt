package com.ftp.keberlanjutanumkmbsc.domain.usecases.auth

import com.ftp.keberlanjutanumkmbsc.data.utils.Resource
import com.ftp.keberlanjutanumkmbsc.domain.model.User
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

interface AuthUseCase {

    fun addUser(user: User): Flow<Resource<Boolean>>
    fun register(email: String, password: String): Flow<Resource<AuthResult>>
    fun login(email: String, password: String): Flow<Resource<AuthResult>>
    fun getUserWithIDUser(idUser: String): Flow<Resource<User>>

}