package com.example.clone_instagram.utils

import com.example.clone_instagram.presentation.register.RegisterViewModel.ValidationState

sealed class AssignState<out T>{
    object Loading : AssignState<Nothing>()
    object Wait : AssignState<Nothing>()
    data class Success<out T>(val data : T) : AssignState<T>()
    data class Error(val message : Exception) : AssignState<Nothing>()
}
