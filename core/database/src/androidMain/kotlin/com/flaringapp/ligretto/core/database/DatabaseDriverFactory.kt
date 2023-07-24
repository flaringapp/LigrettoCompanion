package com.flaringapp.ligretto.core.database

import android.content.Context
import androidx.sqlite.db.SupportSQLiteDatabase
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
        val schemaValue = schema.synchronous()
        return AndroidSqliteDriver(
            schema = schemaValue,
            context = context,
            name = databaseName,
            callback = object : AndroidSqliteDriver.Callback(schemaValue) {
                override fun onOpen(db: SupportSQLiteDatabase) {
                    db.setForeignKeyConstraintsEnabled(true)
                }
            },
        )
    }
}
