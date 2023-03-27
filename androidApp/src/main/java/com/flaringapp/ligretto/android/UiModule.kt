package com.flaringapp.ligretto.android

import org.koin.core.KoinApplication
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.ksp.generated.module

internal fun KoinApplication.uiModule() = modules(UiModule().module)

@Module
@ComponentScan
internal class UiModule
