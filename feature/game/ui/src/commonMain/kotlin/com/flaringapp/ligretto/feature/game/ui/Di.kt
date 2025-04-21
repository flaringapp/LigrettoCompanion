package com.flaringapp.ligretto.feature.game.ui

import org.koin.core.KoinApplication
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.ksp.generated.module

fun KoinApplication.gameUiModule() = modules(UiModule().module)

@Module
@ComponentScan
internal class UiModule
