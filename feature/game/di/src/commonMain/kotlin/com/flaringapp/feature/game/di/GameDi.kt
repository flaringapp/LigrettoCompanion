package com.flaringapp.feature.game.di

import com.flaringapp.ligretto.feature.game.data.dataModule
import com.flaringapp.ligretto.feature.game.domain.domainModule
import org.koin.core.KoinApplication

fun KoinApplication.gameModules() {
    domainModule()
    dataModule()
}
