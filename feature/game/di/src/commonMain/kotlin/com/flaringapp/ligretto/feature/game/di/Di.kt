package com.flaringapp.ligretto.feature.game.di

import com.flaringapp.ligretto.feature.game.data.gameDataModule
import com.flaringapp.ligretto.feature.game.domain.gameDomainModule
import com.flaringapp.ligretto.feature.game.ui.gameUiModule
import org.koin.core.KoinApplication

fun KoinApplication.gameKmmModules() {
    gameDomainModule()
    gameDataModule()
    gameUiModule()
}
