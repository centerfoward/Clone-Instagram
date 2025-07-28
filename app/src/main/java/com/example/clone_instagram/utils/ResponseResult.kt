package com.example.clone_instagram.utils

sealed class ResponseResult <out T> {
    object Loading : ResponseResult<Nothing>()
    data class Success<out T>(val data: T) : ResponseResult<T>()
    data class Failure(val exception: Throwable) : ResponseResult<Nothing>()
}