package com.flaringapp.ligretto.common.di

import com.flaringapp.ligretto.core.database.coreDatabaseModule
import com.flaringapp.ligretto.core.settings.coreSettingsModule
import com.flaringapp.ligretto.feature.game.di.gameKmmModules
import com.flaringapp.ligretto.feature.game.ui.gameUiModule
import com.flaringapp.ligretto.feature.home.di.homeKmmModules
import com.flaringapp.ligretto.feature.home.ui.homeUiModule
import org.koin.core.KoinApplication

internal fun KoinApplication.appModules() {
    coreDatabaseModule()
    coreSettingsModule()
    homeKmmModules()
    homeUiModule()
    gameKmmModules()
    gameUiModule()
}
