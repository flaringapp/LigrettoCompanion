package com.flaringapp.ligretto.core.database

import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import co.touchlab.sqliter.DatabaseConfiguration

actual class DatabaseDriverFactory(
    private val databaseName: String,
) {

    actual suspend fun provideDriver(
        schema: SqlSchema<QueryResult.AsyncValue<Unit>>,
    ): SqlDriver {
        return NativeSqliteDriver(
            schema = schema.synchronous(),
            name = databaseName,
            onConfiguration = { config ->
                config.copy(
                    extendedConfig = DatabaseConfiguration.Extended(foreignKeyConstraints = true),
                )
            },
        )
    }
}
