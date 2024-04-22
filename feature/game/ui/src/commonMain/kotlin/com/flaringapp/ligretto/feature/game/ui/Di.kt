package com.flaringapp.ligretto.feature.game.ui

import com.flaringapp.ligretto.feature.game.ui.close.GameCloseViewModel
import com.flaringapp.ligretto.feature.game.ui.end.GameEndViewModel
import com.flaringapp.ligretto.feature.game.ui.lap.GameLapViewModel
import com.flaringapp.ligretto.feature.game.ui.score.GameScoreViewModel
import com.flaringapp.ligretto.feature.game.ui.start.GameStartViewModel
import org.koin.core.KoinApplication
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import org.koin.ksp.generated.module

fun KoinApplication.gameUiModule() = modules(viewModelModule(), UiModule().module)

@Module
@ComponentScan
internal class UiModule

private fun viewModelModule() = module {

    factoryOf(::GameStartViewModel)
    factoryOf(::GameCloseViewModel)
    factoryOf(::GameScoreViewModel)
    factoryOf(::GameLapViewModel)
    factoryOf(::GameEndViewModel)
}
