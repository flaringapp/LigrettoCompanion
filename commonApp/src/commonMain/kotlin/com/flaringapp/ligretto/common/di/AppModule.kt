package com.flaringapp.ligretto.common.di

import com.flaringapp.ligretto.core.database.coreDatabaseModules
import com.flaringapp.ligretto.core.di.DiDefinitionScope
import com.flaringapp.ligretto.core.settings.coreSettingsModules
import com.flaringapp.ligretto.feature.game.di.gameModules
import com.flaringapp.ligretto.feature.home.di.homeModules

internal fun DiDefinitionScope.appModules() = buildList {
    addAll(coreDatabaseModules())
    addAll(coreSettingsModules())
    addAll(homeModules())
    addAll(gameModules())
}
