package com.flaringapp.ligretto.feature.home.ui

import com.flaringapp.ligretto.core.di.DiDefinitionScope
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.ksp.generated.module

fun DiDefinitionScope.homeUiModule() = UiModule().module

@Module
@ComponentScan
internal class UiModule
