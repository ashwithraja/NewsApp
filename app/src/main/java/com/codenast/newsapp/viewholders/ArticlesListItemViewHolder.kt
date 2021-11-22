package com.codenast.newsapp.viewholders

import com.codenast.domain.model.Articles
import com.codenast.newsapp.R
import com.codenast.newsapp.base.BaseViewHolder
import com.codenast.newsapp.databinding.ArticleItemBinding

class ArticlesListItemViewHolder(private var viewBinder: ArticleItemBinding) :
    BaseViewHolder<Articles>(viewBinder) {
    companion object {
        fun getLayoutId(): Int {
            return R.layout.article_item
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