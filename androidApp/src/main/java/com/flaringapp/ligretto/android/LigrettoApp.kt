package com.flaringapp.ligretto.android

import android.app.Application
import com.flaringapp.ligretto.feature.game.di.gameKmmModules
import com.flaringapp.ligretto.feature.game.ui.gameUiModule
import com.flaringapp.ligretto.feature.home.ui.homeUiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class LigrettoApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@LigrettoApp)
            homeUiModule()
            gameKmmModules()
            gameUiModule()
        }
    }
}
