package com.flaringapp.ligretto.core.database

import org.koin.core.annotation.Configuration
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import kotlinx.coroutines.runBlocking

@Module(includes = [DatabasePlatformModule::class])
@Configuration
class DatabaseModule {

    @Single
    internal fun provideDatabase(driverFactory: DatabaseDriverFactory): Database {
        return runBlocking {
            Database(
                driver = driverFactory.provideDriver(
                    schema = Database.Schema,
                    name = "ligretto.db",
                ),
            )
        }
    }
}
