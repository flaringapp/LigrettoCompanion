package com.flaringapp.ligretto.feature.home.ui

import org.koin.core.KoinApplication
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.ksp.generated.module

fun KoinApplication.homeUiModule() = modules(UiModule().module)

@Module
@ComponentScan
internal class UiModule
