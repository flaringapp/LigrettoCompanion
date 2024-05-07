package com.flaringapp.ligretto.android

import android.app.Application
import com.flaringapp.ligretto.common.di.initDi
import com.flaringapp.ligretto.common.logging.initLogging

class LigrettoApp : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            initLogging()
        }

        initDi()
    }
}
