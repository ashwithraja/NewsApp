package com.codenast.domain.model

/**
 * Holds the data
 * data - actual data
 * error - error object in case of error
 */
sealed class RemoteData<T>(
    val data: T? = null,
    val error : String? = null,
) {
    class Success<T>(data: T?) : RemoteData<T>(data,null)
    class Failure<T>(message: String?) : RemoteData<T>(null,message)
}

