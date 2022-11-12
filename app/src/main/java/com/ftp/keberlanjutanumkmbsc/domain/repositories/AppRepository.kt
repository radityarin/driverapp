package com.ftp.keberlanjutanumkmbsc.domain.repositories

import com.ftp.keberlanjutanumkmbsc.domain.model.User
import com.ftp.keberlanjutanumkmbsc.data.utils.Resource
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

interface AppRepository {

    fun registerUser(email: String, password: String): Flow<Resource<AuthResult>>
    fun loginUser(email: String, password: String): Flow<Resource<AuthResult>>
    fun addUsers(user: User): Flow<Resource<Boolean>>
    fun getUserWithIDUser(idUser: String): Flow<Resource<User>>

}