package com.kavya.aidl_client.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.kavya.aidl_client.R
import com.kavya.aidl_client.databinding.ActivityMainBinding
import com.kavya.aidl_client.presenter.MainPresenterImpl
import timber.log.Timber

class MainViewImpl : AppCompatActivity(), MainView {
    private lateinit var mBinding: ActivityMainBinding
    private val serviceIntent = Intent("com.kavya.aidl_server")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        serviceIntent.`package` = "com.kavya.aidl_server"
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
        if (MainPresenterImpl.getInstance(this).numGeneratorService == null) {
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
        if (MainPresenterImpl.getInstance(this).numGeneratorService != null) {
            MainPresenterImpl.getInstance(this).connection?.let { unbindService(it) }
        } else {
            updateUi("Nothing is bound")
        }
    }
}