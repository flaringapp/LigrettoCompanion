package com.flaringapp.ligretto.core.database

import android.content.Context
import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import app.cash.sqldelight.driver.android.AndroidSqliteDriver

actual class DatabaseDriverFactory(
    private val context: Context,
    private val databaseName: String,
) {

    actual suspend fun provideDriver(
        schema: SqlSchema<QueryResult.AsyncValue<Unit>>,
    ): SqlDriver {
        return AndroidSqliteDriver(
            schema = schema.synchronous(),
            context = context,
            name = databaseName,
        )
    }
}
