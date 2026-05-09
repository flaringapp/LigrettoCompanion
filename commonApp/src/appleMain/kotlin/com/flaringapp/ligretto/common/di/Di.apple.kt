package com.flaringapp.ligretto.common.di

import org.koin.plugin.module.dsl.startKoin

fun initDi() {
    startKoin<LigrettoKoinApp>()
}
