package com.flaringapp.ligretto.android

import android.app.Application
import com.flaringapp.ligretto.core.database.coreDatabaseModule
import com.flaringapp.ligretto.core.settings.coreSettingsModule
import com.flaringapp.ligretto.feature.game.di.gameKmmModules
import com.flaringapp.ligretto.feature.game.ui.gameUiModule
import com.flaringapp.ligretto.feature.home.di.homeKmmModules
import com.flaringapp.ligretto.feature.home.ui.homeUiModule
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class LigrettoApp : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Napier.base(DebugAntilog())
        }

        startKoin {
            androidContext(this@LigrettoApp)
            coreDatabaseModule()
            coreSettingsModule()
            homeKmmModules()
            homeUiModule()
            gameKmmModules()
            gameUiModule()
        }
    }
}
