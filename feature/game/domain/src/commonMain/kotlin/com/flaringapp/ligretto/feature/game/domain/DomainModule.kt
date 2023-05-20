package com.flaringapp.ligretto.feature.game.domain

import org.koin.core.KoinApplication
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module
import org.koin.ksp.generated.module
import kotlinx.datetime.Clock

fun KoinApplication.domainModule() = modules(DomainModule().module)

@Module
@ComponentScan
internal class DomainModule {

    @Factory
    fun clock(): Clock = Clock.System
}
