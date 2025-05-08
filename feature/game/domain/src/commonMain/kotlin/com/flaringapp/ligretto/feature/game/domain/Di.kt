package com.flaringapp.ligretto.feature.game.domain

import com.flaringapp.ligretto.core.di.DiDefinitionScope
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module
import org.koin.ksp.generated.module
import kotlinx.datetime.Clock

fun DiDefinitionScope.gameDomainModule() = DomainModule().module

@Module
@ComponentScan
internal class DomainModule {

    @Factory
    fun clock(): Clock = Clock.System
}
