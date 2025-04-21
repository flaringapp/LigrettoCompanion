package com.flaringapp.ligretto.common.di

import org.koin.core.context.startKoin

fun initDi() {
    startKoin {
        appModules()
    }
}
