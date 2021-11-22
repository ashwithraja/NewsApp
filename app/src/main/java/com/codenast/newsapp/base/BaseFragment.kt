package com.codenast.newsapp.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        loadData()
    }
    protected fun showProgress(progress: View) {
        progress.visibility = View.VISIBLE
    }

    protected fun hideProgress(progress: View) {
        progress.visibility = View.GONE
    }
    abstract fun loadData()

    abstract fun initView()

    /**
     * handles all error cases - network availability
     */
    protected fun handleError() {

    }

    /**
     * should be overridden by all subclass inorder to handle the error case
     */
    abstract fun observeError()

}