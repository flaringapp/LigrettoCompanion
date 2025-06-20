package com.flaringapp.ligretto.common.di

import com.flaringapp.ligretto.core.database.coreDatabaseModules
import com.flaringapp.ligretto.core.di.DiDefinitionScope
import com.flaringapp.ligretto.core.settings.coreSettingsModules
import com.flaringapp.ligretto.feature.game.di.gameModules
import com.flaringapp.ligretto.feature.home.di.homeModules
import org.koin.ksp.generated.module

internal fun DiDefinitionScope.appModules() = buildList {
    add(DispatchersModule().module)
    addAll(coreDatabaseModules())
    addAll(coreSettingsModules())
    addAll(homeModules())
    addAll(gameModules())
}
