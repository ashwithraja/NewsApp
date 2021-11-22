package com.codenast.newsapp.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.codenast.domain.model.Articles
import com.codenast.newsapp.utills.Constants
import com.codenast.newsapp.viewholders.NewsListItemViewHolder

/**
 * Generic recyclar adapter
 * returns generic adaptor
 */
class BaseAdapter<T> : RecyclerView.Adapter<BaseViewHolder<T>>() {
    private var mDataSource: ArrayList<Any> = ArrayList()
    private var mClickListner: ItemClickListner? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        return getViewHolder(viewType, parent)
    }

    private fun getViewHolder(viewType: Int, parent: ViewGroup): BaseViewHolder<T> {
        when (viewType) {
            Constants.ViewHolderType.ARTICLE_LIST -> {
                return NewsListItemViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        NewsListItemViewHolder.getLayoutId(),
                        parent,
                        false
                    )
                ) as BaseViewHolder<T>
            }
        }
        return NewsListItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                NewsListItemViewHolder.getLayoutId(),
                parent,
                false
            )
        ) as BaseViewHolder<T>
    }

    fun setData(dataSource: List<Any>) {
        mDataSource.addAll(dataSource)
        notifyDataSetChanged()
    }

    fun setListenr(listner: ItemClickListner) {
        mClickListner = listner;
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.onBindData(mDataSource[position], position)
        if (mClickListner != null) {
            holder.setListner(mClickListner)
        }
    }

    override fun getItemCount(): Int {
        return mDataSource?.size
    }

    override fun getItemViewType(position: Int): Int {
        if (mDataSource != null) {
            when (mDataSource[position]) {
                is Articles -> return Constants.ViewHolderType.CUSTOM
            }
        }

        //it can be replaced with empty response
        return Constants.ViewHolderType.CUSTOM
    }

    public interface ItemClickListner {
        fun onItemClick(data: Any?, position: Int)
    }
}