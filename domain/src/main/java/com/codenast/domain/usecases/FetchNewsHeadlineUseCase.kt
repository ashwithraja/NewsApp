package com.codenast.domain.usecases

import com.codenast.domain.base.UseCase
import com.codenast.domain.model.Headlines
import com.codenast.domain.model.RemoteData
import com.codenast.domain.repository.NewsRepository
import javax.inject.Inject

class FetchNewsHeadlineUseCase @Inject constructor(var repository: NewsRepository) : UseCase() {
    suspend fun fetchHeadlines() : RemoteData<out Headlines> {
      return  repository.fetchNewsHeadLines()
    }
}