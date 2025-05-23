package com.flaringapp.ligretto.core.database

import com.flaringapp.ligretto.core.di.DiDefinitionScope
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import org.koin.dsl.module
import org.koin.ksp.generated.module
import kotlinx.coroutines.runBlocking

fun DiDefinitionScope.coreDatabaseModules() = listOf(
    platformDatabaseModule(),
    DatabaseModule().module,
)

@Module
internal class DatabaseModule {

    @Single
    fun provideDatabase(driverFactory: DatabaseDriverFactory): Database {
        return runBlocking {
            Database(
                driver = driverFactory.provideDriver(Database.Schema),
            )
        }
    }
}

internal fun platformDatabaseModule() = module {
    factory { databaseDriverFactory("ligretto.db") }
}
