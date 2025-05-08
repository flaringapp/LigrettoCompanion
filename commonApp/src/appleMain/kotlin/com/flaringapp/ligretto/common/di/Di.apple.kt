package com.flaringapp.ligretto.common.di

import com.flaringapp.ligretto.core.di.DiDefinitionScope
import org.koin.core.context.startKoin

fun initDi() {
    startKoin {
        modules(DiDefinitionScope.appModules())
    }
}
