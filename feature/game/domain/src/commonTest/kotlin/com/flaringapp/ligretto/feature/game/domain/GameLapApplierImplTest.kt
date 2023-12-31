package com.flaringapp.ligretto.feature.game.domain

import com.flaringapp.ligretto.feature.game.model.Game
import com.flaringapp.ligretto.feature.game.model.GameId
import com.flaringapp.ligretto.feature.game.model.Lap
import com.flaringapp.ligretto.feature.game.model.LapId
import com.flaringapp.ligretto.feature.player.model.Player
import com.flaringapp.ligretto.feature.game.model.Score
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.datetime.Instant

internal class GameLapApplierImplTest {

    @Test
    fun `Lap with scores is properly applied to a game`() {
        val calculator = GameScoreCalculatorImpl()
        val applier = GameLapApplierImpl(calculator)

        val playerOne = Player(1, "Andrii")
        val playerTwo = Player(2, "Mario")

        val players = listOf(playerOne, playerTwo)
        val initialScores = mapOf(
            playerOne to Score(10),
            playerTwo to Score(20),
        )
        val initialGame = Game(
            id = GameId(1),
            players = players,
            timeStarted = Instant.DISTANT_PAST,
            scores = initialScores,
        )

        val cardsLeft = mapOf(
            playerOne to 0,
            playerTwo to 4,
        )
        val cardsOnTable = mapOf(
            playerOne to 25,
            playerTwo to 16,
        )
        val lap = Lap(
            id = LapId(1),
            number = 1,
            cardsLeft = cardsLeft,
            cardsOnTable = cardsOnTable,
        )

        val actualGame = applier.apply(initialGame, lap)

        val expectedScores = mapOf(
            playerOne to Score(35),
            playerTwo to Score(28),
        )
        val expectedLaps = listOf(lap)

        assertEquals(expectedScores, actualGame.scores)
        assertEquals(expectedLaps, actualGame.completedLaps)
    }
}
