package com.codenast.domain.usecases

import com.codenast.domain.base.UseCase
import com.codenast.domain.model.Like
import com.codenast.domain.model.RemoteData
import com.codenast.domain.repository.NewsRepository
import javax.inject.Inject

class FetchLikesUseCase @Inject constructor(var repository: NewsRepository) : UseCase() {
    data class Param(var url: String?)

    suspend fun execute(data: Param): RemoteData<out Like>? {
        return repository.fetchLike(data.url)
    }
}