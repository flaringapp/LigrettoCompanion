package com.flaringapp.ligretto.core.database

import com.flaringapp.ligretto.core.database.test.TestDatabaseDriverProvider
import kotlin.test.BeforeTest

abstract class QueriesTest<Queries : Any> {

    protected lateinit var queries: Queries

    @BeforeTest
    fun setup() {
        val driver = TestDatabaseDriverProvider.create(Database.Schema)
        val database = Database(driver)
        queries = provideQueries(database)
    }

    protected abstract fun provideQueries(database: Database): Queries
}
