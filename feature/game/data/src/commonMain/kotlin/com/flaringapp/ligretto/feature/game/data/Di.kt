package com.flaringapp.ligretto.feature.game.data

import com.flaringapp.ligretto.core.database.Database
import com.flaringapp.ligretto.feature.game.data.storage.GameStorageDataSource
import com.flaringapp.ligretto.feature.game.data.storage.GameStorageDataSourceImpl
import org.koin.core.KoinApplication
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import org.koin.ksp.generated.module
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.datetime.Clock

fun KoinApplication.gameDataModule() = modules(DataModule().module)

@Module
@ComponentScan
internal class DataModule {

    @Single
    fun provideGameStorageDataSource(
        database: Database,
        clock: Clock,
    ): GameStorageDataSource {
        return GameStorageDataSourceImpl(
            database = database,
            clock = clock,
            dispatcher = Dispatchers.IO,
        )
    }
}
