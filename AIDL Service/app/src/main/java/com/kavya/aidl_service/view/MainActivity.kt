package com.kavya.aidl_service.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.kavya.aidl_service.MainPresenter
import com.kavya.aidl_service.MainView
import com.kavya.aidl_service.R
import com.kavya.aidl_service.ServiceObserver
import com.kavya.aidl_service.databinding.ActivityMainBinding
import com.kavya.aidl_service.model.NumGeneratorService
import com.kavya.aidl_service.presenter.MainPresenterImpl
import timber.log.Timber

class MainActivity : AppCompatActivity(), MainView, ServiceObserver {
    private lateinit var mIntent: Intent
    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )

        mIntent = Intent(this, NumGeneratorService::class.java)

        setUpPresenter()

        bindVariables()
    }

    private fun bindVariables() {
        mBinding.presenter = MainPresenterImpl
    }

    private fun setUpPresenter() {
        MainPresenterImpl.initModel(this.applicationContext)
        MainPresenterImpl.setView(this)
        MainPresenterImpl.registerToServiceStatus(this)
    }

    override fun initiateView() {
        mBinding.statusMsg.text = getString(R.string.ready)
    }


    override fun onStatusUpdate(status: String) {
        mBinding.statusMsg.text = status
    }

    override fun startService() {
        startService(mIntent)
    }

    override fun stopService() {
        stopService(mIntent)
    }
}