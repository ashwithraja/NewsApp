package com.codenast.domain.repository

import com.codenast.domain.base.Repository
import com.codenast.domain.model.Comment
import com.codenast.domain.model.Headlines
import com.codenast.domain.model.Like
import com.codenast.domain.model.RemoteData


interface NewsRepository : Repository {
    suspend fun fetchNewsHeadLines(): RemoteData<out Headlines>
    suspend fun fetchLike(url: String?): RemoteData<out Like>
    suspend fun fetchComment(url: String?): RemoteData<out Comment>
}