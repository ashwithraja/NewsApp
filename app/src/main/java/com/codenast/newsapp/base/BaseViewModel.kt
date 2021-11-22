package com.codenast.newsapp.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open abstract class BaseViewModel : ViewModel() {
    val mErrorObserver: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    abstract fun handleError()

}