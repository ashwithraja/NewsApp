package com.codenast.newsapp.dashBoard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.codenast.domain.model.Articles
import com.codenast.newsapp.R
import com.codenast.newsapp.base.BaseFragment
import com.codenast.newsapp.databinding.FragmentNewsDetailsBinding
import com.codenast.newsapp.viewModel.NewsListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsDetailsFragment : BaseFragment() {

    private lateinit var binding: FragmentNewsDetailsBinding
    private val viewModel: NewsListViewModel by viewModels()
    private var _article: Articles? = null;
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_news_details, container, false
        )
        return binding.root
    }

    override fun loadData() {
        showProgress(binding.progress.root)
        viewModel.updateLikeAndComment(getNews())

        viewModel._article.observe(this, Observer {
            hideProgress(binding.progress.root)
            binding.article = _article
            binding.executePendingBindings()
        })

    }

    override fun initView() {
        _article = getNews()
    }

    override fun observeError() {

    }

    private fun getNews(): Articles? {
        try {
            if (arguments != null) {
                if (arguments?.containsKey("article")!!) {
                    return arguments?.get("article") as Articles
                }
            }
        } catch (e: Exception) {
            return null
        }

        return null
    }

}