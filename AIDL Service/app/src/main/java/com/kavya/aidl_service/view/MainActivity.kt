package com.kavya.aidl_service.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.kavya.aidl_service.MainPresenter
import com.kavya.aidl_service.MainView
import com.kavya.aidl_service.R
import com.kavya.aidl_service.ServiceObserver
import com.kavya.aidl_service.databinding.ActivityMainBinding
import com.kavya.aidl_service.presenter.MainPresenterImpl

class MainActivity : AppCompatActivity(), MainView, ServiceObserver {
    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )

        MainPresenterImpl.setView(this)
        MainPresenterImpl.registerToServiceStatus(this)
    }

    override fun initiateView() {
        mBinding.statusMsg.text = getString(R.string.ready)
    }

    override fun setPresenter(presenter: MainPresenter) {
        mPresenter = presenter
    }

    override fun onStatusUpdate(status: String) {
        mBinding.statusMsg.text = status
    }
}