package com.flaringapp.ligretto.core.database.test

import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import kotlinx.coroutines.runBlocking

object TestDatabaseDriverProvider {

    fun create(
        schema: SqlSchema<QueryResult.AsyncValue<Unit>>,
    ): SqlDriver = runBlocking {
        JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY).also {
            schema.create(it).await()
        }
    }
}
