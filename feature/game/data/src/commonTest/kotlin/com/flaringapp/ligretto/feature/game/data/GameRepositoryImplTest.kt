@file:OptIn(ExperimentalCoroutinesApi::class)

package com.flaringapp.ligretto.feature.game.data

import com.flaringapp.ligretto.core.database.test.TestDatabaseProvider
import com.flaringapp.ligretto.core.settings.test.provider.TestSettingsProvider
import com.flaringapp.ligretto.feature.game.data.cache.GameCacheImpl
import com.flaringapp.ligretto.feature.game.data.settings.GameSettingsImpl
import com.flaringapp.ligretto.feature.game.data.storage.GameStorageDataSourceImpl
import com.flaringapp.ligretto.feature.game.model.GameConfig
import com.flaringapp.ligretto.feature.game.model.GameSnapshot
import com.flaringapp.ligretto.feature.game.model.Player
import com.flaringapp.ligretto.feature.game.model.Score
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue
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

internal class GameRepositoryImplTest {

    private lateinit var settings: GameSettingsImpl
    private lateinit var repository: GameRepositoryImpl

    private lateinit var clockTime: Instant

    @BeforeTest
    fun setup() {
        val dispatcher = StandardTestDispatcher()
        Dispatchers.setMain(dispatcher)

        clockTime = Instant.parse("2023-08-13T18:00:00.00Z")
        val clock = object : Clock {
            override fun now(): Instant = clockTime
        }
        val dataSource = GameStorageDataSourceImpl(
            database = TestDatabaseProvider.create(),
            clock = clock,
            dispatcher = dispatcher,
        )
        settings = GameSettingsImpl(
            settingsProvider = TestSettingsProvider(),
        )
        val mapper = GameRepositoryMapperImpl(
            loadGameMapper = LoadGameRepositoryMapperImpl(
                clock = clock,
            ),
            clock = clock,
        )
        repository = GameRepositoryImpl(
            gameStorageDataSource = dataSource,
            gameSettings = settings,
            gameCache = GameCacheImpl(),
            gameObservables = GameObservablesImpl(),
            mapper = mapper,
        )
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `Start game returns game model with data that matches config`() = runTest {
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

        val game = repository.startGame(gameConfig)

        assertContentEquals(players, game.players)
        assertEquals(clockTime, game.timeStarted)
        assertTrue(game.scores.all { it.value == Score.Zero })
        assertTrue(game.completedLaps.isEmpty())
        assertEquals(gameConfig.targetScore, game.endConditions.score?.targetScore)
        assertEquals(gameConfig.timeLimit, game.endConditions.time?.gameDuration)
    }

    @Test
    fun `Start game sets active game id to settings`() = runTest {
        val players = listOf(
            Player(id = 1, name = "Andreo"),
            Player(id = 2, name = "Olenkka"),
        )
        val gameConfig = GameConfig(
            players = players,
        )

        val game = repository.startGame(gameConfig)

        assertEquals(game.id.value, settings.activeGameId)
    }

    @Test
    fun `Start game updates current game flow`() = runTest {
        val players = listOf(
            Player(id = 1, name = "Andreo"),
            Player(id = 2, name = "Olenkka"),
            Player(id = 3, name = "Alina"),
        )
        val gameConfig = GameConfig(
            players = players,
        )

        val game = repository.startGame(gameConfig)
        val flowGame = repository.currentGameFlow.firstOrNull()

        assertEquals(game, flowGame)
    }

    @Test
    fun `Collect previous game flow caches last value`() = runTest {
        val players = listOf(
            Player(id = 1, name = "Andreo"),
            Player(id = 2, name = "Olenkka"),
            Player(id = 3, name = "Alina"),
        )
        val gameConfig = GameConfig(
            players = players,
        )

        val game = repository.startGame(gameConfig)
        val gameSnapshot = GameSnapshot(
            game = game,
            activeLap = null,
        )

        val flowGame = repository.previousGameFlow.firstOrNull()

        assertEquals(gameSnapshot, flowGame)
        assertEquals(gameSnapshot, repository.getCachedPreviousGame())
    }

    @Test
    fun `End game returns model of currently active game`() = runTest {
        val players = listOf(
            Player(id = 1, name = "Andreo"),
            Player(id = 2, name = "Olenkka"),
            Player(id = 3, name = "Alina"),
        )
        val gameConfig = GameConfig(
            players = players,
        )

        val game = repository.startGame(gameConfig)

        val endGame = repository.endGame()

        assertEquals(game, endGame)
    }

    @Test
    fun `End game nulls active game id in settings`() = runTest {
        val players = listOf(
            Player(id = 1, name = "Andreo"),
            Player(id = 2, name = "Olenkka"),
            Player(id = 3, name = "Alina"),
        )
        val gameConfig = GameConfig(
            players = players,
        )

        repository.startGame(gameConfig)
        repository.endGame()

        assertNull(settings.activeGameId)
    }

    @Test
    fun `End game nulls current game and lap flows`() = runTest {
        val players = listOf(
            Player(id = 1, name = "Andreo"),
            Player(id = 2, name = "Olenkka"),
            Player(id = 3, name = "Alina"),
        )
        val gameConfig = GameConfig(
            players = players,
        )

        repository.startGame(gameConfig)
        repository.startNextLap()
        repository.endGame()

        val flowGame = repository.currentGameFlow.firstOrNull()
        val flowLap = repository.currentLapFlow.firstOrNull()

        assertNull(flowGame)
        assertNull(flowLap)
    }

    @Test
    fun `End game does not modify previous game flow and cached game`() = runTest {
        val players = listOf(
            Player(id = 1, name = "Andreo"),
            Player(id = 2, name = "Olenkka"),
            Player(id = 3, name = "Alina"),
        )
        val gameConfig = GameConfig(
            players = players,
        )

        val game = repository.startGame(gameConfig)
        val gameSnapshot = GameSnapshot(
            game = game,
            activeLap = null,
        )

        repository.endGame()

        val flowGame = repository.previousGameFlow.firstOrNull()
        val cachedGame = repository.getCachedPreviousGame()

        assertEquals(gameSnapshot, flowGame)
        assertEquals(gameSnapshot, cachedGame)
    }

    @Test
    fun `Start next lap returns lap model with expected data`() = runTest {
        val players = listOf(
            Player(id = 1, name = "Andreo"),
            Player(id = 2, name = "Olenkka"),
            Player(id = 3, name = "Alina"),
        )
        val gameConfig = GameConfig(
            players = players,
        )

        repository.startGame(gameConfig)
        val lap = repository.startNextLap()

        assertEquals(1, lap.number)
        assertContentEquals(players, lap.cardsLeft.keys)
        assertContentEquals(players, lap.cardsOnTable.keys)
        assertTrue(lap.cardsLeft.values.all { it == 0 })
        assertTrue(lap.cardsOnTable.values.all { it == 0 })
    }

    @Test
    fun `Start next lap updates current lap flow`() = runTest {
        val players = listOf(
            Player(id = 1, name = "Andreo"),
            Player(id = 2, name = "Olenkka"),
            Player(id = 3, name = "Alina"),
        )
        val gameConfig = GameConfig(
            players = players,
        )

        repository.startGame(gameConfig)
        val lap = repository.startNextLap()
        val flowLap = repository.currentLapFlow.firstOrNull()

        assertEquals(lap, flowLap)
    }

    @Test
    fun `Update lap player cards updates current lap flow with expected data`() = runTest {
        val player = Player(id = 1, name = "Andreo")
        val players = listOf(
            player,
            Player(id = 2, name = "Olenkka"),
            Player(id = 3, name = "Alina"),
        )
        val gameConfig = GameConfig(
            players = players,
        )

        repository.startGame(gameConfig)
        val lap = repository.startNextLap()

        val lapWithNewCards = lap.copy(
            cardsLeft = lap.cardsLeft.toMutableMap().also {
                it[player] = 3
            },
            cardsOnTable = lap.cardsOnTable.toMutableMap().also {
                it[player] = 14
            },
        )

        repository.updateLapPlayerCards(
            lap = lapWithNewCards,
            player = player,
        )

        val flowLap = repository.currentLapFlow.firstOrNull()

        assertEquals(lapWithNewCards, flowLap)
    }

    @Test
    fun `End lap updates current and previous game flow and nulls current lap flow`() = runTest {
        val player = Player(id = 1, name = "Andreo")
        val players = listOf(
            player,
            Player(id = 2, name = "Olenkka"),
            Player(id = 3, name = "Alina"),
        )
        val gameConfig = GameConfig(
            players = players,
        )

        val game = repository.startGame(gameConfig)
        val lap = repository.startNextLap()

        val lapWithNewCards = lap.copy(
            cardsLeft = lap.cardsLeft.toMutableMap().also {
                it[player] = 3
            },
            cardsOnTable = lap.cardsOnTable.toMutableMap().also {
                it[player] = 14
            },
        )

        repository.updateLapPlayerCards(
            lap = lapWithNewCards,
            player = player,
        )

        val gameWithLap = game.withNewLap(
            lap = lapWithNewCards,
            newScores = game.scores.toMutableMap().also {
                it[player] = Score(8)
            },
        )
        val gameSnapshot = GameSnapshot(
            game = gameWithLap,
            activeLap = null,
        )

        repository.endLap(gameWithLap)

        val flowCurrentGame = repository.currentGameFlow.firstOrNull()
        val flowPreviousGame = repository.previousGameFlow.firstOrNull()
        val flowLap = repository.currentLapFlow.firstOrNull()

        assertEquals(gameWithLap, flowCurrentGame)
        assertEquals(gameSnapshot, flowPreviousGame)
        assertNull(flowLap)
    }
}
