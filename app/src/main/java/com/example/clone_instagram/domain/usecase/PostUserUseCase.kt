package com.example.clone_instagram.domain.usecase

import com.example.clone_instagram.domain.repository.UserRepository
import com.example.clone_instagram.utils.ResponseResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostUserUseCase @Inject constructor(
    val repository: UserRepository
) {
    operator fun invoke(uid : String, name : String) : Flow<ResponseResult<Unit>>{
        return repository.postData(uid, name)
    }
}