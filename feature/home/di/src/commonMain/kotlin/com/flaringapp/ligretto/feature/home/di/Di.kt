package com.flaringapp.ligretto.feature.home.di

import com.flaringapp.ligretto.core.di.DiDefinitionScope
import com.flaringapp.ligretto.feature.home.domain.homeDomainModule
import com.flaringapp.ligretto.feature.home.ui.homeUiModule

fun DiDefinitionScope.homeModules() = listOf(
    homeDomainModule(),
    homeUiModule(),
)
