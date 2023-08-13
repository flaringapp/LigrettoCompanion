package com.flaringapp.ligretto.core.database

import app.cash.sqldelight.async.coroutines.awaitAsList
import app.cash.sqldelight.async.coroutines.awaitAsOne
import kotlin.test.assertContentEquals
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

internal class LapQueriesTest : QueriesTest<LapQueries>() {

    override fun provideQueries(database: Database): LapQueries {
        return database.lapQueries
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
