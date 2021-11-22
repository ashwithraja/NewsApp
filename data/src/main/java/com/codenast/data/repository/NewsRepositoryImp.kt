package com.codenast.data.repository

import HeadLineConvertor
import com.codenast.data.model.CommentEntityConvertor
import com.codenast.data.model.LikeEntityConvertor
import com.codenast.data.remote.service.ApiResponseConvertor
import com.codenast.data.remote.service.RetrofitApiService
import com.codenast.data.utills.NetworkUtills
import com.codenast.domain.model.Comment
import com.codenast.domain.model.Headlines
import com.codenast.domain.model.Like
import com.codenast.domain.model.RemoteData
import com.codenast.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImp @Inject constructor() : NewsRepository {
    @Inject
    lateinit var apiService: RetrofitApiService
    override suspend fun fetchNewsHeadLines(): RemoteData<out Headlines> {
        try {
            //Connectivity check should be added here and data can be fetched from local or DB
            val response =
                ApiResponseConvertor.safeApiCall(apiService.getHeadlines(NetworkUtills.getCommonQueryParams()))

            return when (response) {
                is RemoteData.Failure -> RemoteData.Failure(response.data.toString())
                is RemoteData.Success -> {
                    RemoteData.Success(response.data?.let {
                        HeadLineConvertor.mapToDomainEntity(
                            it
                        )
                    })
                }
            }
        } catch (e: Exception) {
            return RemoteData.Failure("network error")
        }
    }

    override suspend fun fetchLike(url: String?): RemoteData<out Like> {
        val response =
            ApiResponseConvertor.safeApiCall(apiService.getLikes(NetworkUtills.getLikeUrl(url)))

        return when (response) {
            is RemoteData.Failure -> RemoteData.Failure(response.data.toString())
            is RemoteData.Success -> {
                RemoteData.Success(response.data?.let {
                    LikeEntityConvertor.convertLikeEntityToDomain(it)
                })
            }
        }
    }

    override suspend fun fetchComment(url: String?): RemoteData<out Comment> {
        val response =
            ApiResponseConvertor.safeApiCall(apiService.getComments(NetworkUtills.getCommentUrl(url)))

        return when (response) {
            is RemoteData.Failure -> RemoteData.Failure(response.data.toString())
            is RemoteData.Success -> {
                RemoteData.Success(response.data?.let {
                    CommentEntityConvertor.convertEntiityToDomain(it)
                })
            }
        }
    }
}
