package com.flaringapp.ligretto.core.database

import app.cash.sqldelight.async.coroutines.awaitAsList
import app.cash.sqldelight.async.coroutines.awaitAsOne
import com.flaringapp.ligretto.core.database.test.TestDatabaseDriverProvider
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GamePlayerQueriesTest {

    private lateinit var queries: GamePlayerQueries

    @Before
    fun setup() {
        val driver = TestDatabaseDriverProvider.create(Database.Schema)
        queries = Database(driver).gamePlayerQueries
    }

    @Test
    fun `Select all inserted by game id`() = runTest {
        val expectedGameId = 1L
        val notExpectedGameId = 2L
        val expectedOne = SelectAllByGameId(
            player_id = 1,
            score = 0,
        )
        val expectedTwo = SelectAllByGameId(
            player_id = 2,
            score = 0,
        )
        val notExpectedThree = SelectAllByGameId(
            player_id = 3,
            score = 0,
        )
        val expectedList = listOf(expectedOne, expectedTwo)

        // Game 1
        queries.insert(
            game_id = expectedGameId,
            player_id = expectedOne.player_id,
        )
        queries.insert(
            game_id = expectedGameId,
            player_id = expectedTwo.player_id,
        )

        // Game 2
        queries.insert(
            game_id = notExpectedGameId,
            player_id = expectedTwo.player_id,
        )
        queries.insert(
            game_id = notExpectedGameId,
            player_id = notExpectedThree.player_id,
        )

        val actual = queries
            .selectAllByGameId(expectedGameId)
            .awaitAsList()

        assertContentEquals(expectedList, actual)
    }

    @Test
    fun `Select after update score is up to date`() = runTest {
        val gameId = 1L
        val player = SelectAllByGameId(
            player_id = 1,
            score = 0,
        )
        val expectedScore = 10L

        queries.insert(
            game_id = gameId,
            player_id = player.player_id,
        )

        queries.updateScore(
            game_id = gameId,
            player_id = player.player_id,
            score = expectedScore,
        )

        val actual = queries
            .selectAllByGameId(gameId)
            .awaitAsOne()

        assertEquals(expectedScore, actual.score)
    }
}
