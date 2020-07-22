package com.kavya.aidl_service.model

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.kavya.aidl_service.IRandomNumGeneratorInterface
import com.kavya.aidl_service.ServiceObserver

/**
 * Created by Kavya P S on 22/07/20.
 */
class NumGeneratorService : Service() {

    private val mObserversList = ArrayList<ServiceObserver>()
    override fun onCreate() {
        super.onCreate()
    }

    private val mBinder = object : IRandomNumGeneratorInterface.Stub() {
        override fun getRandomNumber() {

        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    fun addObserver(observer: ServiceObserver) {
        mObserversList.add(observer)

    }


}