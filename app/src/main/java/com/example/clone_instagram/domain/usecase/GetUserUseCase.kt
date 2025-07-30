package com.example.clone_instagram.domain.usecase

import com.example.clone_instagram.domain.repository.UserRepository
import com.example.clone_instagram.utils.ResponseResult
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(uid : String) : Flow<ResponseResult<DocumentSnapshot>>{
        return repository.getData(uid = uid )
    }
}