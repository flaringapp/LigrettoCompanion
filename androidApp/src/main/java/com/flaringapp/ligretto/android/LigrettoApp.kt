package com.flaringapp.ligretto.android

import android.app.Application
import com.flaringapp.ligretto.common.di.initDi
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

class LigrettoApp : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Napier.base(DebugAntilog())
        }

        initDi()
    }
}
