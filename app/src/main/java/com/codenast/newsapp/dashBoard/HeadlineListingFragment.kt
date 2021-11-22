package com.codenast.newsapp.dashBoard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.codenast.domain.model.Articles
import com.codenast.newsapp.R
import com.codenast.newsapp.base.BaseAdapter
import com.codenast.newsapp.base.BaseFragment
import com.codenast.newsapp.databinding.FragmentNewsListBinding
import com.codenast.newsapp.viewModel.NewsListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HeadlineListingFragment : BaseFragment(), BaseAdapter.ItemClickListner {
    private lateinit var binding: FragmentNewsListBinding
    private val viewModel: NewsListViewModel by viewModels()
    private val mAdapter: BaseAdapter<Articles> by lazy { BaseAdapter() }

    override fun loadData() {
        showProgress(binding.progress.root)
        viewModel.mErrorObserver.observe(this, Observer {
            Log.d("error", it)
        })
        viewModel.fetchNewsList()

        viewModel._newsListLiveData.observe(this, Observer {
            mAdapter.setData(it.articles)
            hideProgress(binding.progress.root)
        })
    }

    override fun initView() {
        mAdapter.setListenr(this)
        binding.rcList.layoutManager = LinearLayoutManager(this.context)
        binding.rcList.adapter = mAdapter
    }

    override fun observeError() {
        viewModel.mErrorObserver.observe(this, Observer {
            handleError()
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_news_list, container, false
        )
        return binding.root
    }

    override fun onItemClick(data: Any?, position: Int) {
        /* var action = SearchProfileFragmentDirections.actionNavSearchToNavProfileDetails(data)
         NavHostFragment.findNavController(this).navigate(action)*/
        var action = HeadlineListingFragmentDirections.actionListingToDetails(data as Articles)
        NavHostFragment.findNavController(this).navigate(action)

    }
}