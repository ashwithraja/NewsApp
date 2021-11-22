package com.codenast.newsapp.viewholders

import com.codenast.domain.model.Articles
import com.codenast.newsapp.R
import com.codenast.newsapp.base.BaseViewHolder
import com.codenast.newsapp.databinding.NewsItemBinding

class NewsListItemViewHolder(private var viewBinder: NewsItemBinding) :
    BaseViewHolder<Articles>(viewBinder) {
    companion object {
        fun getLayoutId(): Int {
            return R.layout.news_item
        }
    }

    override fun <T> onBindData(data: T, position: Int) {
        viewBinder.article = data as Articles
        viewBinder.executePendingBindings()

        viewBinder.root.setOnClickListener {
            mClickLitener?.onItemClick(data, position)
        }
    }
}