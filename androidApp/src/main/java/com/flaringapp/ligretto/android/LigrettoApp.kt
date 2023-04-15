package com.flaringapp.ligretto.android

import android.app.Application
import com.flaringapp.ligretto.core.data.dataModule
import com.flaringapp.ligretto.core.domain.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class LigrettoApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@LigrettoApp)
            dataModule()
            domainModule()
            uiModule()
        }
    }
}
