package com.kavya.aidl_service.model

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.kavya.aidl_service.IRandomNumGeneratorInterface
import com.kavya.aidl_service.ServiceObserver
import timber.log.Timber

/**
 * Created by Kavya P S on 22/07/20.
 */
class NumGeneratorService : Service() {

    private val mObserversList = ArrayList<ServiceObserver>()
    override fun onCreate() {
        super.onCreate()
        updateObservers("Service is created")

    }

    private val mBinder = object : IRandomNumGeneratorInterface.Stub() {
        override fun getRandomNumber() {
            updateObservers("providing a random number")

        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        updateObservers("Bound to this service!")
        return mBinder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        updateObservers("Unbound from this service")
        return false
    }

    fun addObserver(observer: ServiceObserver) {
        mObserversList.add(observer)

    }

    fun updateObservers(status: String) {
        Timber.d(status)
        for (observer in mObserversList) {
            observer.onStatusUpdate(status)
        }
    }

}