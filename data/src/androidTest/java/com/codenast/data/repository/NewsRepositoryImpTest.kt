package com.codenast.data.repository

import com.codenast.data.remote.service.RetrofitApiService
import com.codenast.data.utills.NetworkUtills
import junit.framework.TestCase
import org.junit.Before
import org.mockito.Mockito

class NewsRepositoryImpTest : TestCase() {
    private val apiService = Mockito.mock(RetrofitApiService::class.java)

    @Before
    override fun setUp() {

    }


   suspend fun testFetchNewsHeadLines() {
        apiService.getHeadlines(NetworkUtills.getCommonQueryParams())
    }

    fun testFetchLike() {}

    fun testFetchComment() {}
}