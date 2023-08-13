package com.flaringapp.ligretto.core.database

import app.cash.sqldelight.async.coroutines.awaitAsList
import app.cash.sqldelight.async.coroutines.awaitAsOne
import com.flaringapp.ligretto.core.database.test.TestDatabaseDriverProvider
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class LapPlayerQueriesTest {

    private lateinit var queries: LapPlayerQueries

    @Before
    fun setup() {
        val driver = TestDatabaseDriverProvider.create(Database.Schema)
        queries = Database(driver).lapPlayerQueries
    }

    @Test
    fun `Select all by laps after insert`() = runTest {
        val expectedLapId = 1L
        val notExpectedLapId = 2L

        val playerIds = listOf(1L, 2L, 3L, 4L)

        val expected = playerIds.map { playerId ->
            queries.insert(
                lap_id = expectedLapId,
                player_id = playerId,
            )
            queries.insert(
                lap_id = notExpectedLapId,
                player_id = playerId,
            )
            LapPlayer(
                lap_id = expectedLapId,
                player_id = playerId,
                cards_left = 0,
                cards_on_table = 0,
            )
        }

        val actual = queries
            .selectAllByLaps(
                lap_id = listOf(expectedLapId),
            )
            .awaitAsList()

        assertContentEquals(expected, actual)
    }

    @Test
    fun `Select after updating cards is up to date`() = runTest {
        val lapId = 1L
        val playerId = 1L
        val expectedCardsLeft = 3L
        val expectedCardsOnTable = 13L

        queries.insert(
            lap_id = lapId,
            player_id = playerId,
        )

        queries.updateCards(
            lap_id = lapId,
            player_id = playerId,
            cards_left = expectedCardsLeft,
            cards_on_table = expectedCardsOnTable,
        )

        val actual = queries
            .selectAllByLaps(
                lap_id = listOf(lapId),
            )
            .awaitAsOne()

        assertEquals(expectedCardsLeft, actual.cards_left)
        assertEquals(expectedCardsOnTable, actual.cards_on_table)
    }
}
