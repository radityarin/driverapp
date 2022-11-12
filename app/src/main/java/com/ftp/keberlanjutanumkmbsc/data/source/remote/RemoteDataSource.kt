package com.ftp.keberlanjutanumkmbsc.data.source.remote

import com.ftp.keberlanjutanumkmbsc.domain.model.User
import com.ftp.keberlanjutanumkmbsc.utils.Constants
import com.ftp.keberlanjutanumkmbsc.data.utils.Resource
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await

class RemoteDataSource(
    private val firebaseAuth: FirebaseAuth
) {

    private val mUsersCollection =
        FirebaseFirestore.getInstance().collection(Constants.COLLECTION_USER)

    fun loginUser(email: String, password: String) = flow<Resource<AuthResult>> {
        emit(Resource.Loading())
        val loginAuth = firebaseAuth.signInWithEmailAndPassword(email, password).await()
        emit(Resource.Success(loginAuth))
    }.catch {
        emit(Resource.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)

    fun registerUser(email: String, password: String) = flow<Resource<AuthResult>> {
        emit(Resource.Loading())
        val registerAuth = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
        emit(Resource.Success(registerAuth))
    }.catch {
        println("ERROR " + it.message.toString())
        emit(Resource.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)

    fun addUser(user: User) = flow<Resource<Boolean>> {
        emit(Resource.Loading())
        mUsersCollection.document(user.idUser).set(user).await()
        emit(Resource.Success(true))
    }.catch {
        println("ERROR " + it.message.toString())
        emit(Resource.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)

    fun getUserWithIDUser(idUser: String) = flow<Resource<User>> {
        val snapshot =
            mUsersCollection.document(idUser)
                .get().await()
        val user = snapshot.toObject(User::class.java)
        if (user != null) {
            emit(Resource.Success(user))
        }
    }.catch {
        emit(Resource.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)

}