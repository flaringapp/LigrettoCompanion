package com.flaringapp.ligretto.common.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.plugin.module.dsl.startKoin

fun Application.initDi() {
    startKoin<LigrettoKoinApp> {
        androidContext(this@initDi)
    }
}
