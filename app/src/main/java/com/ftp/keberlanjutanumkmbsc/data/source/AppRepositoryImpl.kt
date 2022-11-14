package com.ftp.keberlanjutanumkmbsc.data.source

import com.ftp.keberlanjutanumkmbsc.data.logic.Kuesioner
import com.ftp.keberlanjutanumkmbsc.data.source.remote.RemoteDataSource
import com.ftp.keberlanjutanumkmbsc.domain.model.User
import com.ftp.keberlanjutanumkmbsc.domain.repositories.AppRepository
import com.ftp.keberlanjutanumkmbsc.data.utils.Resource
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

class AppRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
) : AppRepository {

    override fun loginUser(email: String, password: String): Flow<Resource<AuthResult>> =
        remoteDataSource.loginUser(email, password)

    override fun registerUser(email: String, password: String): Flow<Resource<AuthResult>> =
        remoteDataSource.registerUser(email, password)

    override fun addUsers(user: User): Flow<Resource<Boolean>> = remoteDataSource.addUser(user)

    override fun getUserWithIDUser(idUser: String): Flow<Resource<User>> =
        remoteDataSource.getUserWithIDUser(idUser = idUser)

    override fun addQuestioner(questioner: Kuesioner) =
        remoteDataSource.addQuestioner(questioner)

    override fun getAllQuestioner() = remoteDataSource.getAllQuestioner()

    override fun getListQuestionerWithSpecificID(idUser: String) =
        remoteDataSource.getListQuestionerWithSpecificID(idUser)
}
