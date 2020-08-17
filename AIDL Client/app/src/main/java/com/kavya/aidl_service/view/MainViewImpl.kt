package com.kavya.aidl_service.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.kavya.aidl_service.R
import com.kavya.aidl_service.databinding.ActivityMainBinding
import com.kavya.aidl_service.presenter.MainPresenterImpl
import timber.log.Timber

class MainViewImpl : AppCompatActivity(), MainView {
    private lateinit var mBinding: ActivityMainBinding
    private val serviceIntent = Intent("com.kavya.aidl_service.model.NumGeneratorService")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        serviceIntent.`package` = "com.kavya.aidl_service"
        mBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )

        mBinding.presenter = MainPresenterImpl.getInstance(this)
    }


    override fun updateUi(status: String) {
        Timber.d(status)
        mBinding.output.text = status
    }

    override fun bindService() {
        if (!MainPresenterImpl.getInstance(this).isBound) {
            MainPresenterImpl.getInstance(this).isBound = true
            bindService(
                serviceIntent,
                MainPresenterImpl.getInstance(this).connection,
                Context.BIND_AUTO_CREATE
            )
        } else {
            updateUi("Already bound")
        }
    }

    override fun unBindService() {
        if (MainPresenterImpl.getInstance(this).isBound) {
            MainPresenterImpl.getInstance(this).connection?.let { unbindService(it) }
            MainPresenterImpl.getInstance(this).isBound = false
        } else {
            updateUi("Nothing is bound")
        }
    }
}