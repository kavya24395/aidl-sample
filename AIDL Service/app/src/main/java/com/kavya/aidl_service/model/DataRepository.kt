package com.kavya.aidl_service.model

import android.content.Context
import android.content.Intent
import com.kavya.aidl_service.ServiceObserver
import timber.log.Timber

/**
 * Created by Kavya P S on 25/07/20.
 */
class DataRepository private constructor() : ServiceObserver {
    private val mObserversList = ArrayList<ServiceObserver>()
    private lateinit var mIntent: Intent

    companion object {
        @JvmStatic
        @Volatile
        private var INSTANCE: DataRepository? = null

        @JvmStatic
        fun getInstance(context: Context): DataRepository {
            return when (INSTANCE) {
                null -> {
                    INSTANCE = DataRepository()
                    INSTANCE?.mIntent = Intent(context, NumGeneratorService::class.java)
                    INSTANCE!!
                }

                else -> INSTANCE!!
            }
        }
    }


    fun addObserver(observer: ServiceObserver) {
        mObserversList.add(observer)

    }

    private fun updateObservers(status: String) {
        Timber.d(status)
        for (observer in mObserversList) {
            observer.onStatusUpdate(status)
        }
    }

    override fun onStatusUpdate(status: String) {
        updateObservers(status)
    }
}