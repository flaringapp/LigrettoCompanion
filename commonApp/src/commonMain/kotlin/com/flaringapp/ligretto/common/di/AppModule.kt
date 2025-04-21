package com.flaringapp.ligretto.common.di

import com.flaringapp.ligretto.core.database.coreDatabaseModule
import com.flaringapp.ligretto.core.settings.coreSettingsModule
import com.flaringapp.ligretto.feature.game.di.gameModules
import com.flaringapp.ligretto.feature.home.di.homeModules
import org.koin.core.KoinApplication

internal fun KoinApplication.appModules() {
    coreDatabaseModule()
    coreSettingsModule()
    homeModules()
    gameModules()
}
