package com.codenast.data.remote.service

import com.codenast.domain.model.RemoteData
import retrofit2.Response

object ApiResponseConvertor {

 fun <T> safeApiCall(response: Response<T>): RemoteData<T> {
        try {
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return RemoteData.Success(body)
                }
            }
            return error("${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(errorMessage: String?): RemoteData<T> =
        RemoteData.Failure(errorMessage)

}