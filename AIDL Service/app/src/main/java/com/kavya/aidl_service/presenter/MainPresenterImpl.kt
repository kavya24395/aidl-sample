package com.kavya.aidl_service.presenter

import android.content.Context
import android.view.View
import com.kavya.aidl_service.MainPresenter
import com.kavya.aidl_service.MainView
import com.kavya.aidl_service.ServiceObserver
import com.kavya.aidl_service.model.DataRepository

/**
 * Created by Kavya P S on 22/07/20.
 */
object MainPresenterImpl : MainPresenter {
    private lateinit var mMainView: MainView
    private lateinit var mModel: DataRepository

    override fun setView(view: MainView) {
        mMainView = view
        setUpForUi()
    }

    override fun initModel(context: Context) {
        mModel = DataRepository.getInstance(context)
    }

    override fun registerToServiceStatus(observer: ServiceObserver) {
        mModel.addObserver(observer)
    }

    override fun onStartServiceClick(view: View) {
        mMainView.startService()
    }

    override fun onStopServiceClick(view: View) {
        mMainView.stopService()
    }

    override fun setUpForUi() {
        mMainView.initiateView()
    }

}