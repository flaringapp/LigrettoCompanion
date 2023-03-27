package com.flaringapp.ligretto.android

import android.app.Application
import com.flaringapp.ligretto.commonModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class LigrettoApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@LigrettoApp)
            commonModule()
            uiModule()
        }
    }
}
