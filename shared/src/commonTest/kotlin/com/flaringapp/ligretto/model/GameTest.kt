package com.flaringapp.ligretto.model

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

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

    private fun createGame(
        id: GameId = GameId(0),
        players: List<Player> = listOf(Player(1, "Andrii")),
        scores: Map<Player, Score> = players.associateWith { Score.Zero },
        completedLaps: List<Lap> = emptyList(),
    ) = Game(
        id = id,
        players = players,
        scores = scores,
        completedLaps = completedLaps,
    )

    private fun createLap(
        number: Int,
        players: List<Player> = listOf(Player(1, "Andrii")),
        cardsLeft: Map<Player, Int> = players.associateWith { 0 },
        cardsOnTable: Map<Player, Int> = players.associateWith { 0 },
    ) = Lap(
        number = number,
        cardsLeft = cardsLeft,
        cardsOnTable = cardsOnTable,
    )
}
