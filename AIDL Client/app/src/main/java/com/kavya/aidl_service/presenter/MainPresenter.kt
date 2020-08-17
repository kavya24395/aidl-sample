package com.kavya.aidl_service.presenter

import com.kavya.aidl_service.BasePresenter

/**
 * Created by Kavya P S on 26/07/20.
 */
interface MainPresenter : BasePresenter {
    fun onBindCLick()

    fun onUnBindClick()

    fun onFetchNumberClick()
}