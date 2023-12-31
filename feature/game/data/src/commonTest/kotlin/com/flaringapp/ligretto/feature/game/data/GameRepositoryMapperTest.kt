package com.flaringapp.ligretto.feature.game.data

import com.flaringapp.ligretto.feature.game.data.storage.StartGameStorageDto
import com.flaringapp.ligretto.feature.game.model.Game
import com.flaringapp.ligretto.feature.game.model.GameConfig
import com.flaringapp.ligretto.feature.game.model.GameId
import com.flaringapp.ligretto.feature.player.model.Player
import com.flaringapp.ligretto.feature.game.model.Score
import com.flaringapp.ligretto.feature.game.model.end.GameEndConditions
import com.flaringapp.ligretto.feature.game.model.end.GameEndScoreCondition
import com.flaringapp.ligretto.feature.game.model.end.GameEndTimeCondition
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

class GameRepositoryMapperTest {

    private lateinit var clock: Clock
    private lateinit var mapper: GameRepositoryMapper

    private lateinit var clockTime: Instant

    @BeforeTest
    fun setup() {
        clockTime = Instant.parse("2023-08-13T18:00:00.00Z")
        clock = object : Clock {
            override fun now(): Instant = clockTime
        }
        val loadGameMapper = LoadGameRepositoryMapperImpl(
            clock = clock,
        )
        mapper = GameRepositoryMapperImpl(
            loadGameMapper = loadGameMapper,
            clock = clock,
        )
    }

    @Test
    fun `Map new game without end conditions returns expected data`() {
        val timeStarted = Instant.parse("2023-08-13T18:00:00.00Z")

        val players = listOf(
            Player(id = 1, name = "Andreo"),
            Player(id = 2, name = "Olenkka"),
            Player(id = 3, name = "Alina"),
        )
        val gameConfig = GameConfig(
            players = players,
        )

        val startGameDto = StartGameStorageDto(
            id = 1,
            timeStarted = timeStarted.toEpochMilliseconds(),
            playerIds = listOf(1, 2, 3)
        )

        val expected = Game(
            id = GameId(1),
            players = players,
            timeStarted = timeStarted,
            scores = players.associateWith { Score.Zero },
            completedLaps = emptyList(),
            endConditions = GameEndConditions(
                score = null,
                time = null,
            )
        )

        val actual = mapper.mapNewGame(
            config = gameConfig,
            dto = startGameDto,
        )

        assertEquals(expected, actual)
    }

    @Test
    fun `Map new game with end conditions returns expected data`() {
        val timeStarted = Instant.parse("2023-08-13T18:00:00.00Z")

        val players = listOf(
            Player(id = 1, name = "Andreo"),
            Player(id = 2, name = "Olenkka"),
            Player(id = 3, name = "Alina"),
            Player(id = 4, name = "Mario"),
        )
        val gameConfig = GameConfig(
            players = players,
            targetScore = Score(156),
            timeLimit = 2.hours + 56.minutes,
        )

        val startGameDto = StartGameStorageDto(
            id = 1,
            timeStarted = timeStarted.toEpochMilliseconds(),
            playerIds = listOf(1, 2, 3, 4)
        )

        val expected = Game(
            id = GameId(1),
            players = players,
            timeStarted = timeStarted,
            scores = players.associateWith { Score.Zero },
            completedLaps = emptyList(),
            endConditions = GameEndConditions(
                score = GameEndScoreCondition(
                    targetScore = gameConfig.targetScore!!,
                ),
                time = GameEndTimeCondition(
                    gameDuration = gameConfig.timeLimit!!,
                    clock = clock,
                ),
            )
        )

        val actual = mapper.mapNewGame(
            config = gameConfig,
            dto = startGameDto,
        )

        assertEquals(expected, actual)
    }
}
