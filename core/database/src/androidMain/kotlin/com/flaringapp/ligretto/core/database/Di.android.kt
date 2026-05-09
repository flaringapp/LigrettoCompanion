package com.flaringapp.ligretto.core.database

import android.content.Context
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module

@Module
internal actual class DatabasePlatformModule {

    @Factory
    fun provideDatabaseDriverFactory(
        context: Context,
    ): DatabaseDriverFactory {
        return AndroidDatabaseDriverFactory(context)
    }
}
