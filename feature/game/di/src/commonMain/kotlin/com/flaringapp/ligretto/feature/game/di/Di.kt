package com.flaringapp.ligretto.feature.game.di

import com.flaringapp.ligretto.core.di.DiDefinitionScope
import com.flaringapp.ligretto.feature.game.data.gameDataModule
import com.flaringapp.ligretto.feature.game.domain.gameDomainModule
import com.flaringapp.ligretto.feature.game.ui.gameUiModule

fun DiDefinitionScope.gameModules() = listOf(
    gameDomainModule(),
    gameDataModule(),
    gameUiModule(),
)
