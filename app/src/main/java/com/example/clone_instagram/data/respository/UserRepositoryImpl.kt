package com.example.clone_instagram.data.respository

import com.example.clone_instagram.data.source.FirebaseRemoteDataSource
import com.example.clone_instagram.domain.repository.UserRepository
import com.example.clone_instagram.utils.ResponseResult
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val dataSource : FirebaseRemoteDataSource
) : UserRepository {
    override fun postData(
        uid: String,
        name: String
    ): Flow<ResponseResult<Unit>> {
        return dataSource.postData(uid,name)
    }

    override fun getData(uid: String): Flow<ResponseResult<DocumentSnapshot>> {
        return dataSource.getData(uid)
    }

    override fun validNickname(name: String): Flow<ResponseResult<String>> {
        return dataSource.validNickname(name)
    }
}