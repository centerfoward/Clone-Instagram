package com.example.clone_instagram.domain.usecase

import com.example.clone_instagram.domain.repository.AuthRepository
import com.example.clone_instagram.utils.ResponseResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    operator fun invoke(email: String, password: String): Flow<ResponseResult<Unit>> {
        return repository.login(email, password)
    }
}