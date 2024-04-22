package com.flaringapp.ligretto.feature.home.ui

import com.flaringapp.ligretto.feature.home.ui.home.HomeViewModel
import org.koin.core.KoinApplication
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import org.koin.ksp.generated.module

fun KoinApplication.homeUiModule() = modules(viewModelModule(), UiModule().module)

@Module
@ComponentScan
internal class UiModule

private fun viewModelModule() = module {

    factoryOf(::HomeViewModel)
}
