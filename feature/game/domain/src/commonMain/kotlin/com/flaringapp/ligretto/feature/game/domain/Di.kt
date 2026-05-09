package com.flaringapp.ligretto.feature.game.domain

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Configuration
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module
import kotlin.time.Clock

@Module
@ComponentScan
@Configuration
class GameDomainModule {

    @Factory
    fun clock(): Clock = Clock.System
}
