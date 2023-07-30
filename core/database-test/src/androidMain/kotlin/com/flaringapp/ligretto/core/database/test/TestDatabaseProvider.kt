package com.flaringapp.ligretto.core.database.test

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.flaringapp.ligretto.core.database.Database
import kotlinx.coroutines.runBlocking

object TestDatabaseProvider {

    fun createDatabase(
        driver: SqlDriver = createDriver()
    ): Database {
        return Database(driver)
    }

    fun createDriver(): SqlDriver {
        return JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY).also {
            runBlocking {
                Database.Schema.create(it).await()
            }
        }
    }
}
