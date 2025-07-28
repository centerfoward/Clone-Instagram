package com.example.clone_instagram.domain.usecase

import com.example.clone_instagram.domain.repository.AuthRepository
import com.example.clone_instagram.utils.AssignState
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AssignUseCase @Inject constructor(
    val repository: AuthRepository
)  {
    operator fun invoke(email: String, password: String): Flow<AssignState<FirebaseUser?>> {
        return repository.assign(email, password)
    }
}