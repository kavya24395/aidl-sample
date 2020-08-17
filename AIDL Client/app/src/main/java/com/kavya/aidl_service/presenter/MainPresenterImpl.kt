package com.kavya.aidl_service.presenter

import android.content.ComponentName
import android.content.ServiceConnection
import android.os.IBinder
import android.os.RemoteException
import com.kavya.aidl_service.IRandomNumGeneratorInterface
import com.kavya.aidl_service.view.MainViewImpl

/**
 * Created by Kavya P S on 26/07/20.
 */
class MainPresenterImpl private constructor(var viewImpl: MainViewImpl) : MainPresenter {
    companion object {
        @JvmStatic
        @Volatile
        private var INSTANCE: MainPresenterImpl? = null

        @JvmStatic
        fun getInstance(viewImpl: MainViewImpl): MainPresenterImpl {
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: MainPresenterImpl(viewImpl).also { INSTANCE = it }
                return INSTANCE!!
            }
            return INSTANCE!!
        }
    }

    var numGeneratorService: IRandomNumGeneratorInterface? = null
    var isBound = false
    var connection: ServiceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            isBound = false
            viewImpl.updateUi("Disconnected from service successfully")
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            isBound = true
            numGeneratorService = IRandomNumGeneratorInterface.Stub.asInterface(service)
            viewImpl.updateUi("Connected to service successfully")
        }
    }


    override fun onBindCLick() {
        viewImpl.bindService()
    }

    override fun onUnBindClick() {
        viewImpl.unBindService()
    }

    override fun onFetchNumberClick() {
        try {
            if(isBound){
                numGeneratorService?.let {
                    viewImpl.updateUi("Fetched numebr is ${it.randomNumber}")
                    return
                }
            }
            viewImpl.updateUi("Not bound to any service")
        } catch (e: RemoteException) {
            viewImpl.updateUi("Remote Exception caught! ${e.message}")
        }
    }

}