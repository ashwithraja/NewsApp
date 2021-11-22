package com.codenast.data.remote.service

import HeadLinesEntity
import com.codenast.data.model.CommentEntity
import com.codenast.data.model.LikeEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.QueryMap
import retrofit2.http.Url

interface RetrofitApiService {

    @GET("top-headlines")
    @Headers("Content-Type:application/json")
    suspend fun getHeadlines(
        @QueryMap(encoded = true) queryMap: Map<String, String>?
    ): Response<HeadLinesEntity>

    @GET
    @Headers("Content-Type:application/json")
    suspend fun getComments(
        @Url apiUrl: String?
    ): Response<CommentEntity>

    @GET
    @Headers("Content-Type:application/json")
    suspend fun getLikes(
        @Url apiUrl: String?
    ): Response<LikeEntity>
}