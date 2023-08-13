package com.flaringapp.ligretto.core.database.test

import com.flaringapp.ligretto.core.database.Database
import com.flaringapp.ligretto.core.util.database.test.TestDatabaseDriverProvider

object TestDatabaseProvider {

    fun create(): Database {
        val driver = TestDatabaseDriverProvider.createBlocking(Database.Schema)
        return Database(driver)
    }
}
