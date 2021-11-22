package com.codenast.data.remote.response

sealed class BaseResponse<out T>() {
    data class Success<out T : Any>(val data: T) : BaseResponse<T>()
    data class Error<out T : Any>(val error: T) : BaseResponse<T>()
}