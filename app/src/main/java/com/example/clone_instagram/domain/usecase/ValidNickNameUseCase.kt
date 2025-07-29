package com.example.clone_instagram.domain.usecase

import com.example.clone_instagram.domain.repository.UserRepository
import com.example.clone_instagram.utils.ResponseResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ValidNickNameUseCase @Inject constructor(
    val repository: UserRepository
) {
    operator fun invoke(name : String) : Flow<ResponseResult<String>>{
        return repository.validNickname(name)
    }
}