package com.example.clone_instagram.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clone_instagram.domain.usecase.LoginUseCase
import com.example.clone_instagram.utils.ResponseResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {
    private val _loginState = MutableStateFlow<ResponseResult<Unit>>(ResponseResult.Loading)
    val loginState : StateFlow<ResponseResult<Unit>> = _loginState

    fun login(email : String, password : String){
        viewModelScope.launch {
            loginUseCase(email, password).collect{ result ->
                _loginState.value = result
            }
        }
    }
}