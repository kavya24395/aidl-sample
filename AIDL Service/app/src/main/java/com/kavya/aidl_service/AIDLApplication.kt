package com.kavya.aidl_service

import android.app.Application
import timber.log.Timber

/**
 * Created by Kavya P S on 23/07/20.
 */

class AIDLApplication: Application(){
    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }

}