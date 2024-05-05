package com.flaringapp.ligretto.feature.home.di

import com.flaringapp.ligretto.feature.home.domain.homeDomainModule
import com.flaringapp.ligretto.feature.home.ui.homeUiModule
import org.koin.core.KoinApplication

fun KoinApplication.homeKmmModules() {
    homeDomainModule()
    homeUiModule()
}
