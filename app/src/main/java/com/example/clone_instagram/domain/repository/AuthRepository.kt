package com.example.clone_instagram.domain.repository

import com.example.clone_instagram.utils.AssignState
import com.example.clone_instagram.utils.ResponseResult
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun login(email: String, password: String): Flow<ResponseResult<Unit>>
    fun assign(email: String, password : String) : Flow<AssignState<FirebaseUser?>>
}