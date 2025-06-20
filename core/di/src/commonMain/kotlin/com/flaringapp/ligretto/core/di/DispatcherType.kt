package com.flaringapp.ligretto.core.di

import org.koin.core.annotation.Named

object DispatcherType {

    @Named
    annotation class IO

    @Named
    annotation class Default

    @Named
    annotation class Main
}
