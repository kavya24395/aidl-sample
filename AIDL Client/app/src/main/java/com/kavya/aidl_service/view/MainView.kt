package com.kavya.aidl_service.view

import com.kavya.aidl_service.BaseView

/**
 * Created by Kavya P S on 26/07/20.
 */
interface MainView : BaseView {
    fun updateUi(status: String)

    fun bindService()

    fun unBindService()
}