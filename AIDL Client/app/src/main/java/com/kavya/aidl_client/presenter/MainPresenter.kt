package com.kavya.aidl_client.presenter

import com.kavya.aidl_client.BasePresenter

/**
 * Created by Kavya P S on 26/07/20.
 */
interface MainPresenter : BasePresenter {
    fun onBindCLick()

    fun onUnBindClick()

    fun onFetchNumberClick()
}