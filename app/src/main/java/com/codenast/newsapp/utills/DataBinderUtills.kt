package com.codenast.newsapp.utills

import android.text.TextUtils
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.codenast.newsapp.R

class DataBinderUtills {
    companion object {
        @BindingAdapter("srcImage")
        @JvmStatic
        fun setImage(view: ImageView, url: String?) {
            if (view != null && !TextUtils.isEmpty(url)) {
                Glide.with(view.context).load(url)
                    .placeholder(view.resources.getDrawable(R.drawable.ic_launcher_background))
                    .into(view)
            }
        }

    }
}