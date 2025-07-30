package com.example.clone_instagram.domain.repository

import com.example.clone_instagram.utils.ResponseResult
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun postData(uid: String, name: String): Flow<ResponseResult<Unit>>
    fun getData(uid: String): Flow<ResponseResult<DocumentSnapshot>>
    fun validNickname(name: String): Flow<ResponseResult<String>>
}