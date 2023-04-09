package com.flaringapp.ligretto

import org.koin.core.KoinApplication
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module
import org.koin.ksp.generated.module
import kotlinx.datetime.Clock

fun KoinApplication.commonModule() = modules(CommonModule().module)

@Module
@ComponentScan
internal class CommonModule {

    @Factory
    fun clock(): Clock = Clock.System
}
