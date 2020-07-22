package com.kavya.aidl_service

/**
 * Created by Kavya P S on 22/07/20.
 */

interface MainPresenter : MainActivityContract.BasePresenter {
    fun setView(view: MainView)

    fun registerToServiceStatus(observer: ServiceObserver)

}