package com.codenast.newsapp.base

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/**
 * Base class for vieHolders
 * accepts listner to communiate with items and generic data
 */
abstract class BaseViewHolder<T>(itemView: ViewBinding) : RecyclerView.ViewHolder(itemView?.root) {
    var mClickLitener: BaseAdapter.ItemClickListner? = null

    abstract class BaseViewHolder<T>(itemView: ViewBinding) :
        RecyclerView.ViewHolder(itemView?.root) {
    }

    abstract fun <T> onBindData(data: T, position: Int)

    fun setListner(listner: BaseAdapter.ItemClickListner?) {
        mClickLitener = listner
    }
}