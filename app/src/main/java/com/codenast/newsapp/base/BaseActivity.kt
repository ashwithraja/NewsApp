package com.codenast.newsapp.base

import android.app.ProgressDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

open abstract class BaseActivity : AppCompatActivity() {
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun showProgress() {
        if (!isFinishing) {
            if (!::progressDialog.isInitialized) {
                progressDialog = ProgressDialog(this)
            }
            progressDialog.show()
        }
    }

    fun cancelProgress() {
        if (!isFinishing && ::progressDialog.isInitialized && progressDialog.isShowing) {
            progressDialog.dismiss()
        }
    }
}