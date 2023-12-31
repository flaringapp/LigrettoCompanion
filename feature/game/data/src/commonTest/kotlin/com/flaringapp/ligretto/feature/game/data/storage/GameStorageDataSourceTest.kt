@file:OptIn(ExperimentalCoroutinesApi::class)

package com.flaringapp.ligretto.feature.game.data.storage

import com.flaringapp.ligretto.core.database.test.TestDatabaseProvider
import com.flaringapp.ligretto.feature.game.model.GameConfig
import com.flaringapp.ligretto.feature.game.model.GameId
import com.flaringapp.ligretto.feature.game.model.LapId
import com.flaringapp.ligretto.feature.player.model.Player
import com.flaringapp.ligretto.feature.game.model.Score
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import com.flaringapp.ligretto.core.database.LapPlayer as DatabaseLapPlayer
import com.flaringapp.ligretto.core.database.Player as DatabasePlayer
import com.flaringapp.ligretto.core.database.SelectAllByGameId as DatabaseGamePlayer
import com.flaringapp.ligretto.core.database.SelectAllByGameIdNumberAscending as DatabaseLap

internal class GameStorageDataSourceTest {

    private lateinit var clockTime: Instant
    private lateinit var storage: GameStorageDataSource

    @BeforeTest
    fun setup() {
        val dispatcher = StandardTestDispatcher()
        Dispatchers.setMain(dispatcher)

        clockTime = Instant.parse("2023-08-13T18:00:00.00Z")
        val clock = object : Clock {
            override fun now(): Instant = clockTime
        }

        storage = GameStorageDataSourceImpl(
            database = TestDatabaseProvider.create(),
            clock = clock,
            dispatcher = dispatcher,
        )
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `Get game data after starting one without end conditions`() = runTest {
        val players = listOf(
            Player(id = 1, name = "Andreo"),
            Player(id = 2, name = "Olenkka"),
            Player(id = 3, name = "Alina"),
        )
        val gameConfig = GameConfig(
            players = players,
        )

        testStartGame(gameConfig)
    }

    @Test
    fun `Get game data after starting one with end conditions`() = runTest {
        val players = listOf(
            Player(id = 1, name = "Andreo"),
            Player(id = 2, name = "Olenkka"),
            Player(id = 3, name = "Alina"),
        )
        val gameConfig = GameConfig(
            players = players,
            targetScore = Score(150),
            timeLimit = 2.hours + 34.minutes,
        )

        testStartGame(gameConfig)
    }

    private suspend fun testStartGame(
        gameConfig: GameConfig,
    ) {
        val startDto = storage.startGame(gameConfig)
        val flowDto = storage.lastGameFlow.firstOrNull()
        val getDto = storage.getGameData(startDto.id)

        val expectedTimeStarted = clockTime.toEpochMilliseconds()
        val expectedScoreLimit = gameConfig.targetScore?.value?.toLong()
        val expectedDurationHours = gameConfig.timeLimit?.inWholeHours
        val expectedDurationMinutes = gameConfig.timeLimit?.let {
            (it - expectedDurationHours!!.hours).inWholeMinutes
        }
        val expectedGamePlayers = gameConfig.players.map {
            DatabaseGamePlayer(player_id = it.id, score = 0)
        }
        val expectedPlayers = gameConfig.players.map {
            DatabasePlayer(id = it.id, name = it.name)
        }

        assertEquals(expectedTimeStarted, flowDto?.time_started)
        assertNull(flowDto?.completed_lap_id)
        assertEquals(expectedScoreLimit, flowDto?.target_score)
        assertEquals(expectedDurationMinutes, flowDto?.duration_minutes)
        assertContentEquals(expectedGamePlayers, getDto.gamePlayers)
        assertContentEquals(emptyList(), getDto.laps)
        assertContentEquals(emptyList(), getDto.lapsPlayers)
        assertContentEquals(expectedPlayers, getDto.players)
    }

    @Test
    fun `Get game data after starting new lap contains added lap and lap players`() = runTest {
        val players = listOf(
            Player(id = 1, name = "Andreo"),
            Player(id = 2, name = "Olenkka"),
            Player(id = 3, name = "Alina"),
        )
        val gameConfig = GameConfig(
            players = players,
        )

        val lapNumbers = listOf(1, 2)

        val startDto = storage.startGame(gameConfig)
        lapNumbers.forEach {
            storage.startNextLap(
                gameId = GameId(startDto.id),
                lapNumber = it,
            )
        }

        val getDto = storage.getGameData(startDto.id)

        val expectedLaps = lapNumbers.mapIndexed { index, lapNumber ->
            DatabaseLap(id = index + 1L, number = lapNumber.toLong())
        }
        val expectedLapPlayers = expectedLaps.flatMap { lap ->
            players.map { player ->
                DatabaseLapPlayer(
                    lap_id = lap.id,
                    player_id = player.id,
                    cards_left = 0,
                    cards_on_table = 0,
                )
            }
        }

        assertContentEquals(expectedLaps, getDto.laps)
        assertContentEquals(expectedLapPlayers, getDto.lapsPlayers)
    }

    @Test
    fun `Get get data after updating lap player cards`() = runTest {
        val players = listOf(
            Player(id = 1, name = "Andreo"),
            Player(id = 2, name = "Olenkka"),
            Player(id = 3, name = "Alina"),
        )
        val gameConfig = GameConfig(
            players = players,
        )
        val lapNumber = 1

        val expectedPlayerTwoCardsLeft = 3
        val expectedPlayerTwoCardsOnTable = 14
        val expectedPlayerThreeCardsLeft = 2
        val expectedPlayerThreeCardsOnTable = 8

        val startGameDto = storage.startGame(gameConfig)
        val lapId = storage.startNextLap(
            gameId = GameId(startGameDto.id),
            lapNumber = lapNumber,
        )

        storage.updateLapPlayerCards(
            lapId = LapId(lapId),
            playerId = 2,
            cardsLeft = expectedPlayerTwoCardsLeft,
            cardsOnTable = expectedPlayerTwoCardsOnTable,
        )

        storage.updateLapPlayerCards(
            lapId = LapId(lapId),
            playerId = 3,
            cardsLeft = expectedPlayerThreeCardsLeft,
            cardsOnTable = expectedPlayerThreeCardsOnTable,
        )

        val getDto = storage.getGameData(startGameDto.id)

        val actualPlayerTwo = getDto.lapsPlayers.find {
            it.lap_id == lapId && it.player_id == 2L
        }
        val actualPlayerThree = getDto.lapsPlayers.find {
            it.lap_id == lapId && it.player_id == 3L
        }

        assertEquals(expectedPlayerTwoCardsLeft, actualPlayerTwo?.cards_left?.toInt())
        assertEquals(expectedPlayerTwoCardsOnTable, actualPlayerTwo?.cards_on_table?.toInt())

        assertEquals(expectedPlayerThreeCardsLeft, actualPlayerThree?.cards_left?.toInt())
        assertEquals(expectedPlayerThreeCardsOnTable, actualPlayerThree?.cards_on_table?.toInt())
    }

    @Test
    fun `Get game data after ending lap updates completed lap and player scores`() = runTest {
        val players = listOf(
            Player(id = 1, name = "Andreo"),
            Player(id = 2, name = "Olenkka"),
            Player(id = 3, name = "Alina"),
        )
        val gameConfig = GameConfig(
            players = players,
        )
        val lapNumber = 1

        val expectedPlayerCardsLeft = listOf(7, 4, 0)
        val expectedPlayerCardsOnTable = listOf(23, 10, 14)
        val expectedPlayerScores = listOf(7, 2, 14)

        val startGameDto = storage.startGame(gameConfig)
        val lapId = storage.startNextLap(
            gameId = GameId(startGameDto.id),
            lapNumber = lapNumber,
        )

        players.forEachIndexed { index, player ->
            storage.updateLapPlayerCards(
                lapId = LapId(lapId),
                playerId = player.id,
                cardsLeft = expectedPlayerCardsLeft[index],
                cardsOnTable = expectedPlayerCardsOnTable[index],
            )
        }

        storage.endLap(
            gameId = GameId(startGameDto.id),
            lapId = LapId(lapId),
            playerScores = players.mapIndexed { index, player ->
                player.id to Score(expectedPlayerScores[index])
            }.toMap(),
        )

        val flowDto = storage.lastGameFlow.firstOrNull()
        val getDto = storage.getGameData(startGameDto.id)

        val expectedGamePlayers = players.mapIndexed { index, player ->
            DatabaseGamePlayer(
                player_id = player.id,
                score = expectedPlayerScores[index].toLong(),
            )
        }

        val actualGamePlayers = players.map { player ->
            getDto.gamePlayers.find { it.player_id == player.id }
        }

        assertEquals(lapId, flowDto?.completed_lap_id)
        assertContentEquals(expectedGamePlayers, actualGamePlayers)
    }
}
