package com.flaringapp.ligretto.core.database

import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module

@Module
internal actual class DatabasePlatformModule {

    @Factory
    fun provideDatabaseDriverFactory(): DatabaseDriverFactory {
        return NativeDatabaseDriverFactory()
    }
}
