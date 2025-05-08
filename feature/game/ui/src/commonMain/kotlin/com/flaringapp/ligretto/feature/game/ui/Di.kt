package com.flaringapp.ligretto.feature.game.ui

import com.flaringapp.ligretto.core.di.DiDefinitionScope
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.ksp.generated.module

fun DiDefinitionScope.gameUiModule() = UiModule().module

@Module
@ComponentScan
internal class UiModule
