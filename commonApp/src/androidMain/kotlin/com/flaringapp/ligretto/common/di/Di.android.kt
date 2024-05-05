package com.flaringapp.ligretto.common.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

fun Application.initDi() {
    startKoin {
        androidContext(this@initDi)
        appModules()
    }
}
