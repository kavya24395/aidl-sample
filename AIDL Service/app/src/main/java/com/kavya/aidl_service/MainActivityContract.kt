package com.kavya.aidl_service

/**
 * Created by Kavya P S on 22/07/20.
 */

class MainActivityContract {
    interface BaseView<T> {
    }

    interface BasePresenter {
        fun setUpForUi()
    }
}