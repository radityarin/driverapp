package com.ftp.keberlanjutanumkmbsc.data.source.remote

import com.ftp.keberlanjutanumkmbsc.data.logic.Kuesioner
import com.ftp.keberlanjutanumkmbsc.data.utils.Resource
import com.ftp.keberlanjutanumkmbsc.domain.model.User
import com.ftp.keberlanjutanumkmbsc.utils.Constants
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
    private val mQuestionersCollection =
        FirebaseFirestore.getInstance().collection(Constants.COLLECTION_QUESTIONER)

    fun loginUser(email: String, password: String) = flow<Resource<AuthResult>> {
        emit(Resource.Loading())
        val loginAuth = firebaseAuth.signInWithEmailAndPassword(email, password).await()
        emit(Resource.Success(loginAuth))
    }.catch {
        println("ERROR " + it.message.toString())
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
        println("ERROR " + it.message.toString())
        emit(Resource.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)

    fun getAllQuestioner() = flow<Resource<List<Kuesioner>>> {
        val snapshot = mQuestionersCollection.get().await()
        val questioners = snapshot.toObjects(Kuesioner::class.java)
        val namedQuestioner = mutableListOf<Kuesioner>()
        questioners.forEachIndexed { index, kuesioner ->
            kuesioner.namaKuesioner = "Perspektif " + (index + 1)
            namedQuestioner.add(
                kuesioner
            )
        }
        emit(Resource.Success(namedQuestioner))
    }.catch {
        println("ERROR " + it.message.toString())
        emit(Resource.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)

    fun getListQuestionerWithSpecificID(idUser: String) =
        flow<Resource<List<Kuesioner>>> {
            val snapshot =
                mQuestionersCollection.whereEqualTo(
                    Constants.COLLECTION_GENERAL_ATTRIBUTE_ID_USER,
                    idUser
                ).get().await()
            val questioners = snapshot.toObjects(Kuesioner::class.java)
            val namedQuestioner = mutableListOf<Kuesioner>()
            questioners.forEachIndexed { index, kuesioner ->
                kuesioner.namaKuesioner = "Perspektif " + (index + 1)
                namedQuestioner.add(
                    kuesioner
                )
            }
            emit(Resource.Success(namedQuestioner))
        }.catch {
            println("ERROR " + it.message.toString())
            emit(Resource.Error(it.message.toString()))
        }.flowOn(Dispatchers.IO)

    fun addQuestioner(questioner: Kuesioner) = flow<Resource<Boolean>> {
        emit(Resource.Loading())
        mQuestionersCollection.document(questioner.idKuesioner).set(questioner).await()
        emit(Resource.Success(true))
    }.catch {
        println("ERROR " + it.message.toString())
        emit(Resource.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)

}