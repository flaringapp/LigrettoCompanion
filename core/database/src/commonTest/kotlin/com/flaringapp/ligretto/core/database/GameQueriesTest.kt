package com.flaringapp.ligretto.core.database

import app.cash.sqldelight.async.coroutines.awaitAsOne
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Instant

internal class GameQueriesTest : QueriesTest<GameQueries>() {

    override fun provideQueries(database: Database): GameQueries {
        return database.gameQueries
    }

    @Test
    fun `Select last game after inserting one with null end conditions`() = runTest {
        testInsertAndSelectLast(
            timeStarted = Instant.parse("2023-08-13T18:00:00.00Z").toEpochMilliseconds(),
            targetScore = null,
            durationMinutes = null,
        )
    }

    @Test
    fun `Select last game after inserting one with present end conditions`() = runTest {
        testInsertAndSelectLast(
            timeStarted = Instant.parse("2023-08-13T18:00:00.00Z").toEpochMilliseconds(),
            targetScore = 150,
            durationMinutes = 84,
        )
    }

    private suspend fun testInsertAndSelectLast(
        timeStarted: Long,
        targetScore: Long?,
        durationMinutes: Long?,
    ) {
        queries.insert(
            time_started = timeStarted,
            target_score = targetScore,
            duration_minutes = durationMinutes,
        )

        val actual = queries
            .selectLast()
            .awaitAsOne()

        assertEquals(timeStarted, actual.time_started)
        assertEquals(targetScore, actual.target_score)
        assertEquals(durationMinutes, actual.duration_minutes)
    }

    @Test
    fun `Select last game after update completed lap id is up to date`() = runTest {
        queries.insert(
            time_started = Instant.parse("2023-08-13T18:00:00.00Z").toEpochMilliseconds(),
            target_score = null,
            duration_minutes = null,
        )

        val id = queries
            .rowid()
            .awaitAsOne()

        queries.updateCompletedLapId(
            id = id,
            completed_lap_id = 1,
        )

        val actual = queries
            .selectLast()
            .awaitAsOne()

        assertEquals(1, actual.completed_lap_id)
    }
}
