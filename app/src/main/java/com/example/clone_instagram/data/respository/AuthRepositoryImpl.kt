package com.example.clone_instagram.data.respository

import com.example.clone_instagram.data.source.AuthRemoteDataSource
import com.example.clone_instagram.domain.repository.AuthRepository
import com.example.clone_instagram.utils.AssignState
import com.example.clone_instagram.utils.ResponseResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val remote: AuthRemoteDataSource
) : AuthRepository {
    override fun login(
        email: String,
        password: String
    ): Flow<ResponseResult<Unit>> {
        return remote.login(email, password)
    }

    override fun assign(
        email: String,
        password: String
    ): Flow<AssignState<FirebaseUser?>> {
        return remote.assign(email,password)
    }
}