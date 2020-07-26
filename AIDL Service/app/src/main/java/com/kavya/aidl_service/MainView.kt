package com.kavya.aidl_service

/**
 * Created by Kavya P S on 22/07/20.
 */

interface MainView : MainActivityContract.BaseView<MainPresenter> {
    fun initiateView()

    fun startService()
    
    fun stopService()

}