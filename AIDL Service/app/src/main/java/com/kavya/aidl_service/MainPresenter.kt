package com.kavya.aidl_service

import android.content.Context
import android.view.View

/**
 * Created by Kavya P S on 22/07/20.
 */

interface MainPresenter : MainActivityContract.BasePresenter {
    fun setView(view: MainView)

    fun initModel(context: Context)

    fun registerToServiceStatus(observer: ServiceObserver)

    fun onStartServiceClick(view: View)

    fun onStopServiceClick(view: View)

}