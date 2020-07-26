package com.kavya.aidl_service.model

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import com.kavya.aidl_service.IRandomNumGeneratorInterface
import com.kavya.aidl_service.ServiceObserver
import timber.log.Timber
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList

/**
 * Created by Kavya P S on 22/07/20.
 */
class NumGeneratorService : Service() {
    private val mObserversList = ArrayList<ServiceObserver>()
    private val numGenExecutor = Executors.newSingleThreadScheduledExecutor()
    private val random = Random()

    @Volatile
    private var number = -1

    private val uiHandler: Handler = Handler(Looper.getMainLooper())

    private val numberUpdater = Runnable {
        number = random.nextInt(100)
        updateObservers("Number generated is $number")
    }
    private val periodicRunnable = Runnable {
        if (!numGenExecutor.isShutdown)
            uiHandler.post(numberUpdater)
    }


    override fun onCreate() {
        super.onCreate()

        addObserver(DataRepository.getInstance(this.applicationContext))
        updateObservers("Service is created")
        numGenExecutor.scheduleAtFixedRate(periodicRunnable, 0, 1, TimeUnit.SECONDS)
    }

    private val mBinder = object : IRandomNumGeneratorInterface.Stub() {
        override fun getRandomNumber(): Int {
            updateObservers("providing a random number")
            return number
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


    fun updateObservers(status: String) {
        Timber.d(status)
        for (observer in mObserversList) {
            observer.onStatusUpdate(status)
        }
    }

    private fun addObserver(observer: ServiceObserver) {
        mObserversList.add(observer)
    }

    private fun addObservers(observers: List<ServiceObserver>) {
        mObserversList.addAll(observers)
    }

    override fun onDestroy() {
        super.onDestroy()
        numGenExecutor.shutdownNow()
        updateObservers("Service is destroyed")
    }
}