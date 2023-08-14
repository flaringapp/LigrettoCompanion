package com.flaringapp.ligretto.feature.game.model

import com.flaringapp.ligretto.feature.game.model.end.GameEndConditions
import com.flaringapp.ligretto.feature.game.model.end.GameEndTimeCondition
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue
import kotlin.time.Duration.Companion.minutes
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

internal class GameTest {

    @Test
    fun `last lap is null when there is no completed laps`() {
        val game = createGame(
            completedLaps = emptyList(),
        )

        assertNull(game.lastLap)
    }

    @Test
    fun `last lap is equal to the last item in completed laps list`() {
        val laps = listOf(
            createLap(1),
            createLap(2),
        )
        val game = createGame(
            completedLaps = laps,
        )

        assertEquals(laps.last(), game.lastLap)
    }

    @Test
    fun `completed laps count is zero when there is no completed laps`() {
        val game = createGame(
            completedLaps = emptyList(),
        )

        assertEquals(0, game.completedLapsCount)
    }

    @Test
    fun `completed laps count is equals to completed laps list size`() {
        val laps = listOf(
            createLap(1),
            createLap(2),
        )
        val game = createGame(
            completedLaps = laps,
        )

        assertEquals(2, game.completedLapsCount)
    }

    @Test
    fun `pending lap number is one when there is no completed laps`() {
        val game = createGame(
            completedLaps = emptyList(),
        )

        assertEquals(1, game.pendingLapNumber)
    }

    @Test
    fun `pending lap number is equals to completed laps list size plus one`() {
        val laps = listOf(
            createLap(1),
            createLap(2),
        )
        val game = createGame(
            completedLaps = laps,
        )

        assertEquals(3, game.pendingLapNumber)
    }

    @Test
    fun `adding new lap to game applies new score and lap`() {
        val players = listOf(
            Player(1, "Andrii"),
            Player(2, "Mario"),
        )
        val scores = mapOf(
            players[0] to Score(1),
            players[1] to Score(2),
        )
        val game = createGame(players = players)
        val lap = createLap(
            number = 1,
            players = players,
        )

        val newGame = game.withNewLap(lap, scores)

        assertEquals(Score(1), newGame.scores[players[0]])
        assertEquals(Score(2), newGame.scores[players[1]])
    }

    @Test
    fun `matches end conditions is false if end conditions do not match`() {
        val game = createGame(
            endConditions = GameEndConditions(),
        )

        assertFalse(game.matchesEndConditions)
    }

    @Test
    fun `matches end conditions is true if end conditions match`() {
        val timeStarted = Instant.parse("2023-04-09T18:00:00.00Z")
        val testClock = object : Clock {
            override fun now(): Instant = Instant.parse("2023-04-09T19:00:01.00Z")
        }
        val endConditions = GameEndConditions(
            time = GameEndTimeCondition(
                gameDuration = 60.minutes,
                clock = testClock,
            ),
        )
        val game = createGame(
            timeStarted = timeStarted,
            endConditions = endConditions,
        )

        assertTrue(game.matchesEndConditions)
    }

    private fun createGame(
        id: GameId = GameId(0),
        players: List<Player> = listOf(Player(1, "Andrii")),
        timeStarted: Instant = Instant.DISTANT_PAST,
        scores: Map<Player, Score> = players.associateWith { Score.Zero },
        completedLaps: List<Lap> = emptyList(),
        endConditions: GameEndConditions = GameEndConditions(),
    ) = Game(
        id = id,
        players = players,
        timeStarted = timeStarted,
        scores = scores,
        completedLaps = completedLaps,
        endConditions = endConditions,
    )

    private fun createLap(
        number: Int,
        id: LapId = LapId(number.toLong()),
        players: List<Player> = listOf(Player(1, "Andrii")),
        cardsLeft: Map<Player, Int> = players.associateWith { 0 },
        cardsOnTable: Map<Player, Int> = players.associateWith { 0 },
    ) = Lap(
        id = id,
        number = number,
        cardsLeft = cardsLeft,
        cardsOnTable = cardsOnTable,
    )
}
