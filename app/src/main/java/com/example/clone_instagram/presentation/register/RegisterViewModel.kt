package com.example.clone_instagram.presentation.register

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clone_instagram.utils.ResponseResult
import com.example.clone_instagram.domain.usecase.AssignUseCase
import com.example.clone_instagram.utils.AssignState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val AssignUseCase : AssignUseCase
) : ViewModel() {
    private val _registerState = MutableStateFlow<AssignState<FirebaseUser?>>(AssignState.Loading)
    val registerState: StateFlow<AssignState<FirebaseUser?>> = _registerState

    private val _validationState = MutableStateFlow<ValidationState>(ValidationState.Empty)
    val validationState: StateFlow<ValidationState> = _validationState

    fun register(email: String, password: String, confirmPassword: String) {
        when {
            email.isBlank() || password.isBlank() -> {
                _validationState.value = ValidationState.Error("이메일과 비밀번호를 입력해주세요")
            }
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                _validationState.value = ValidationState.Error("유효한 이메일 형식을 입력해주세요")
            }
            password.length < 6 -> {
                _validationState.value = ValidationState.Error("비밀번호는 최소 6자 이상이어야 합니다")
            }
            confirmPassword.isBlank() -> {
                _validationState.value = ValidationState.Error("비밀번호 확인란을 입력해주세요")
            }
            password != confirmPassword -> {
                _validationState.value = ValidationState.Error("비밀번호가 일치하지 않습니다")
            }
            else -> {
                _validationState.value = ValidationState.Valid
                createAccount(email, password) // ✅ 회원가입 요청
            }
        }
    }


    private fun createAccount(email: String, password: String) {
        viewModelScope.launch {
            AssignUseCase(email, password).collect {
                _registerState.value = it
            }
        }
    }

    sealed class ValidationState {
        object Empty : ValidationState()
        object Valid : ValidationState()
        data class Error(val message: String) : ValidationState()
    }

}