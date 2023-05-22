package com.flaringapp.ligretto.android

import android.app.Application
import com.flaringapp.feature.game.di.gameModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class LigrettoApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@LigrettoApp)
            gameModules()
            uiModule()
        }
    }
}
