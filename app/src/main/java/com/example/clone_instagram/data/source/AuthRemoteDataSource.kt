package com.example.clone_instagram.data.source

import com.example.clone_instagram.utils.AssignState
import com.example.clone_instagram.utils.ResponseResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(
    private val firebaseAuth: FirebaseAuth
)  {
    fun login(email: String, password: String): Flow<ResponseResult<Unit>> = flow {
        emit(ResponseResult.Loading)
        try {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
            emit(ResponseResult.Success(Unit))
        } catch (e: Exception) {
            emit(ResponseResult.Failure(e))
        }
    }

    fun assign(email : String, password : String) : Flow<AssignState<FirebaseUser?>> = flow{
        emit(AssignState.Loading)
        try{
            emit(AssignState.Wait)
            val result = firebaseAuth.createUserWithEmailAndPassword(email,password).await()
            emit(AssignState.Success(result.user))
        }catch (e : Exception){
            emit(AssignState.Error(e))
        }
    }

}