package com.flaringapp.ligretto.usecase

import com.flaringapp.ligretto.GameScoreCalculatorImpl
import com.flaringapp.ligretto.GameStorageImpl
import com.flaringapp.ligretto.model.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

internal class EndLapUseCaseImplTest {

    @Test
    fun `Lap with scores are properly calculated and saved`() {
        val storage = GameStorageImpl()
        val calculator = GameScoreCalculatorImpl()
        val useCase = EndLapUseCaseImpl(
            gameStorage = storage,
            scoreCalculator = calculator,
        )

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
            scores = initialScores,
            completedLaps = emptyList(),
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
            number = 1,
            cardsLeft = cardsLeft,
            cardsOnTable = cardsOnTable,
        )

        storage.gameFlow.value = initialGame
        storage.lapFlow.value = lap

        useCase.invoke()

        val actualGame = storage.gameFlow.value
        val actualLap = storage.lapFlow.value

        val expectedScores = mapOf(
            playerOne to Score(35),
            playerTwo to Score(28),
        )
        val expectedLaps = listOf(lap)

        assertNotNull(actualGame)
        assertNull(actualLap)

        assertEquals(expectedScores, actualGame.scores)
        assertEquals(expectedLaps, actualGame.completedLaps)
    }
}
