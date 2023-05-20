package com.flaringapp.ligretto.android

import android.app.Application
import com.flaringapp.ligretto.feature.game.data.dataModule
import com.flaringapp.ligretto.feature.game.domain.domainModule
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
