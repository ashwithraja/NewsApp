package com.codenast.newsapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.codenast.domain.model.Articles
import com.codenast.domain.model.Headlines
import com.codenast.domain.model.RemoteData
import com.codenast.domain.usecases.FetchCommentsUseCase
import com.codenast.domain.usecases.FetchLikesUseCase
import com.codenast.domain.usecases.FetchNewsHeadlineUseCase
import com.codenast.newsapp.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val fetchNewsHeadlineUseCase: FetchNewsHeadlineUseCase,
    private val fetchComments: FetchCommentsUseCase,
    private val fetchLikesUseCase: FetchLikesUseCase
) : BaseViewModel() {
    public val _newsListLiveData: MutableLiveData<Headlines> = MutableLiveData<Headlines>()
    val _article: MutableLiveData<Articles> = MutableLiveData<Articles>()

    fun fetchNewsList() {
        viewModelScope.launch(Dispatchers.IO) {
            val value = fetchNewsHeadlineUseCase.fetchHeadlines()
            withContext(Dispatchers.Main) {
                when (value) {
                    is RemoteData.Failure -> mErrorObserver.value = "messsgae";
                    is RemoteData.Success -> _newsListLiveData.value = value.data
                }
            }
        }
    }

    fun updateLikeAndComment(articles: Articles?) {
        viewModelScope.launch(Dispatchers.IO) {

            val comment =
                async { fetchComments.execute(FetchCommentsUseCase.Param(articles?.appUrl)) }.await()

            val like =
                async { fetchLikesUseCase.execute(FetchLikesUseCase.Param(articles?.appUrl)) }.await()

            when (comment) {
                is RemoteData.Failure -> {
                }
                is RemoteData.Success -> {
                    articles?.comment = comment.data?.count
                }
            }

            when (like) {
                is RemoteData.Failure -> {
                }
                is RemoteData.Success -> {
                    articles?.like = comment.data?.count
                }
            }
            withContext(
                Dispatchers.Main
            ) {
                _article.value = articles
            }
        }
    }

    override fun handleError() {

    }
}