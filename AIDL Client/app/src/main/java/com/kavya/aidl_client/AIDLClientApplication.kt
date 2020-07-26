package com.kavya.aidl_client

import android.app.Application
import timber.log.Timber

/**
 * Created by Kavya P S on 26/07/20.
 */
class AIDLClientApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}