package com.flaringapp.ligretto.common.di

import com.flaringapp.ligretto.core.di.DispatcherType
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

@Module
class DispatchersModule {

    @Factory
    @DispatcherType.IO
    fun provideIoDispatcher() = Dispatchers.IO

    @Factory
    @DispatcherType.Default
    fun provideDefaultDispatcher() = Dispatchers.Default

    @Factory
    @DispatcherType.Main
    fun provideMainDispatcher() = Dispatchers.Main
}
