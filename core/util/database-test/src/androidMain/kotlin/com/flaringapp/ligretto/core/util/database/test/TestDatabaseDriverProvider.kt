package com.flaringapp.ligretto.core.util.database.test

import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import kotlinx.coroutines.runBlocking

object TestDatabaseDriverProvider {

    fun createBlocking(
        schema: SqlSchema<QueryResult.AsyncValue<Unit>>,
    ): SqlDriver = runBlocking {
        create(schema)
    }

    suspend fun create(
        schema: SqlSchema<QueryResult.AsyncValue<Unit>>,
    ): SqlDriver {
        return JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY).also {
            schema.create(it).await()
        }
    }
}
