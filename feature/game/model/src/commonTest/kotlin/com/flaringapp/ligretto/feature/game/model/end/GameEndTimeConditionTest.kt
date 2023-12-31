package com.flaringapp.ligretto.feature.game.model.end

import com.flaringapp.ligretto.feature.game.model.Game
import com.flaringapp.ligretto.feature.game.model.GameId
import com.flaringapp.ligretto.feature.player.model.Player
import com.flaringapp.ligretto.feature.game.model.Score
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

internal class GameEndTimeConditionTest {

    @Test
    fun `returns false given less time has passed than duration is set`() {
        val result = matches(
            gameStartTime = Instant.parse("2023-04-04T18:00:00.00Z"),
            nowTime = Instant.parse("2023-04-04T18:20:00.00Z"),
            gameDuration = 30.minutes
        )

        assertFalse(result)
    }

    @Test
    fun `returns true given more time has passed than duration is set`() {
        val result = matches(
            gameStartTime = Instant.parse("2023-04-04T18:00:00.00Z"),
            nowTime = Instant.parse("2023-04-04T19:00:00.00Z"),
            gameDuration = 40.minutes
        )

        assertTrue(result)
    }

    private fun matches(
        gameStartTime: Instant,
        nowTime: Instant,
        gameDuration: Duration,
    ): Boolean {
        val nowClock = object : Clock {
            override fun now(): Instant = nowTime
        }
        val condition = GameEndTimeCondition(
            gameDuration = gameDuration,
            clock = nowClock,
        )
        val game = mockGame(
            condition = condition,
            timeStarted = gameStartTime,
        )

        return condition.matches(game)
    }

    private fun mockGame(
        condition: GameEndTimeCondition,
        timeStarted: Instant,
    ): Game {
        val players = listOf(
            Player(1, "Andreo"),
            Player(2, "Mario"),
        )

        return Game(
            id = GameId(1),
            players = players,
            timeStarted = timeStarted,
            scores = players.associateWith { Score.Zero },
            completedLaps = emptyList(),
            endConditions = GameEndConditions(time = condition),
        )
    }
}
