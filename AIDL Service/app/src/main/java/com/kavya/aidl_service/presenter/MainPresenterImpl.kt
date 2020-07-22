package com.kavya.aidl_service.presenter

import com.kavya.aidl_service.MainPresenter
import com.kavya.aidl_service.MainView
import com.kavya.aidl_service.ServiceObserver
import com.kavya.aidl_service.model.NumGeneratorService

/**
 * Created by Kavya P S on 22/07/20.
 */
object MainPresenterImpl : MainPresenter {
    private lateinit var mMainView: MainView
    private lateinit var mModel: NumGeneratorService

    override fun setView(view: MainView) {
        mMainView = view
        setUpForUi()
    }

    override fun registerToServiceStatus(observer: ServiceObserver) {
        mModel.addObserver(observer)
    }

    override fun setUpForUi() {
        mMainView.initiateView()
    }

}