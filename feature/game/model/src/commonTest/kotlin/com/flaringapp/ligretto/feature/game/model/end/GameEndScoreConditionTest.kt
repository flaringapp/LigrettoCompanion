package com.flaringapp.ligretto.feature.game.model.end

import com.flaringapp.ligretto.feature.game.model.Game
import com.flaringapp.ligretto.feature.game.model.GameId
import com.flaringapp.ligretto.feature.player.model.Player
import com.flaringapp.ligretto.feature.game.model.Score
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlinx.datetime.Instant

internal class GameEndScoreConditionTest {

    @Test
    fun `returns false fiven all players have less score than target`() {
        val scores = mapOf(
            Player(1, "Andreo") to Score(10),
            Player(1, "Mario") to Score(32),
            Player(1, "Alina") to Score(-12),
        )

        val result = matches(
            scores = scores,
            targetScore = Score(100),
        )

        assertFalse(result)
    }

    @Test
    fun `returns true given one player has more score than target`() {
        val scores = mapOf(
            Player(1, "Andreo") to Score(10),
            Player(1, "Mario") to Score(32),
            Player(1, "Alina") to Score(142),
        )

        val result = matches(
            scores = scores,
            targetScore = Score(100),
        )

        assertTrue(result)
    }

    @Test
    fun `returns true given two players have more scores than target`() {
        val scores = mapOf(
            Player(1, "Andreo") to Score(10),
            Player(1, "Mario") to Score(121),
            Player(1, "Alina") to Score(142),
        )

        val result = matches(
            scores = scores,
            targetScore = Score(100),
        )

        assertTrue(result)
    }

    private fun matches(
        scores: Map<Player, Score>,
        targetScore: Score,
    ): Boolean {
        val condition = GameEndScoreCondition(targetScore)
        val game = mockGame(
            scores = scores,
            condition = condition,
        )

        return condition.matches(game)
    }

    private fun mockGame(
        scores: Map<Player, Score>,
        condition: GameEndScoreCondition,
    ): Game {
        return Game(
            id = GameId(1),
            players = scores.keys.toList(),
            timeStarted = Instant.DISTANT_PAST,
            scores = scores,
            completedLaps = emptyList(),
            endConditions = GameEndConditions(score = condition),
        )
    }
}
