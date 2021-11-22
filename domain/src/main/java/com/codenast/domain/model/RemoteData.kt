package com.codenast.domain.model

sealed class RemoteData<T>(
    val data: T? = null,
    error : String? = null,
) {
    class Success<T>(data: T?) : RemoteData<T>(data,null)
    class Failure<T>(message: String?) : RemoteData<T>(null,message)
}

