package com.flaringapp.ligretto.core.database

import app.cash.sqldelight.async.coroutines.awaitAsList
import app.cash.sqldelight.async.coroutines.awaitAsOne
import com.flaringapp.ligretto.core.database.test.TestDatabaseDriverProvider
import kotlin.test.assertContentEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class LapQueriesTest {

    private lateinit var queries: LapQueries

    @Before
    fun setup() {
        val driver = TestDatabaseDriverProvider.create(Database.Schema)
        queries = Database(driver).lapQueries
    }

    @Test
    fun `Insert several laps and select all in expected order`() = runTest {
        val expectedGameId = 1L
        val notExpectedGameId = 2L
        val expectedNumbers = listOf(1L, 2L, 3L)
        val notExpectedNumbers = listOf(1L, 2L, 3L, 4L)

        val expected = expectedNumbers.map { number ->
            queries.insert(
                game_id = expectedGameId,
                number = number,
            )

            val id = queries
                .rowid()
                .awaitAsOne()

            SelectAllByGameIdNumberAscending(
                id = id,
                number = number,
            )
        }

        notExpectedNumbers.forEach {
            queries.insert(
                game_id = notExpectedGameId,
                number = it,
            )
        }

        val actual = queries
            .selectAllByGameIdNumberAscending(expectedGameId)
            .awaitAsList()

        assertContentEquals(expected, actual)
    }
}
