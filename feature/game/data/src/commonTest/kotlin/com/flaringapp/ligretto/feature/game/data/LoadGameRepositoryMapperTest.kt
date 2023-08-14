package com.flaringapp.ligretto.feature.game.data

import com.flaringapp.ligretto.feature.game.data.storage.GameDataStorageDto
import com.flaringapp.ligretto.feature.game.model.Game
import com.flaringapp.ligretto.feature.game.model.GameId
import com.flaringapp.ligretto.feature.game.model.Lap
import com.flaringapp.ligretto.feature.game.model.LapId
import com.flaringapp.ligretto.feature.game.model.Player
import com.flaringapp.ligretto.feature.game.model.Score
import com.flaringapp.ligretto.feature.game.model.end.GameEndConditions
import com.flaringapp.ligretto.feature.game.model.end.GameEndScoreCondition
import com.flaringapp.ligretto.feature.game.model.end.GameEndTimeCondition
import com.flaringapp.ligretto.core.database.Game as DatabaseGame
import com.flaringapp.ligretto.core.database.LapPlayer as DatabaseLapPlayer
import com.flaringapp.ligretto.core.database.Player as DatabasePlayer
import com.flaringapp.ligretto.core.database.SelectAllByGameId as DatabaseGamePlayer
import com.flaringapp.ligretto.core.database.SelectAllByGameIdNumberAscending as DatabaseLap
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

internal class LoadGameRepositoryMapperTest {

    private lateinit var clock: Clock
    private lateinit var mapper: LoadGameRepositoryMapper

    private lateinit var clockTime: Instant

    @BeforeTest
    fun setup() {
        clockTime = Instant.parse("2023-08-13T18:00:00.00Z")
        clock = object : Clock {
            override fun now(): Instant = clockTime
        }
        mapper = LoadGameRepositoryMapperImpl(
            clock = clock,
        )
    }

    @Test
    fun `Map started game with no end conditions returns expected data`() {
        val timeStarted = Instant.parse("2023-08-13T18:00:00.00Z")

        val gameDto = DatabaseGame(
            id = 1,
            time_started = timeStarted.toEpochMilliseconds(),
            completed_lap_id = null,
            target_score = null,
            duration_hours = null,
            duration_minutes = null,
        )
        val playersDto = listOf(
            DatabasePlayer(id = 1, name = "Andreo"),
            DatabasePlayer(id = 2, name = "Olenkka"),
            DatabasePlayer(id = 3, name = "Alina"),
        )
        val data = GameDataStorageDto(
            gamePlayers = listOf(
                DatabaseGamePlayer(player_id = 1, score = 0),
                DatabaseGamePlayer(player_id = 2, score = 0),
                DatabaseGamePlayer(player_id = 3, score = 0),
            ),
            laps = emptyList(),
            lapsPlayers = emptyList(),
            players = playersDto,
        )

        val expectedPlayers = listOf(
            Player(id = 1, name = "Andreo"),
            Player(id = 2, name = "Olenkka"),
            Player(id = 3, name = "Alina"),
        )
        val expected = Game(
            id = GameId(1),
            players = expectedPlayers,
            timeStarted = timeStarted,
            scores = expectedPlayers.associateWith { Score.Zero },
            completedLaps = emptyList(),
            endConditions = GameEndConditions(
                score = null,
                time = null,
            ),
        )

        val actual = mapper.map(
            game = gameDto,
            data = data,
        )

        assertEquals(expected, actual)
    }

    @Test
    fun `Map filled game with end conditions returns expected data`() {
        val timeStarted = Instant.parse("2023-08-13T18:00:00.00Z")

        val gameDto = DatabaseGame(
            id = 1,
            time_started = timeStarted.toEpochMilliseconds(),
            completed_lap_id = 2,
            target_score = 153,
            duration_hours = 2,
            duration_minutes = 34,
        )
        val playersDto = listOf(
            DatabasePlayer(id = 1, name = "Andreo"),
            DatabasePlayer(id = 2, name = "Olenkka"),
            DatabasePlayer(id = 3, name = "Alina"),
            DatabasePlayer(id = 4, name = "Mario"),
        )
        val data = GameDataStorageDto(
            gamePlayers = listOf(
                DatabaseGamePlayer(player_id = 1, score = 65),
                DatabaseGamePlayer(player_id = 2, score = 74),
                DatabaseGamePlayer(player_id = 3, score = 56),
                DatabaseGamePlayer(player_id = 4, score = -32),
            ),
            laps = listOf(
                DatabaseLap(id = 1, number = 1),
                DatabaseLap(id = 2, number = 2),
                DatabaseLap(id = 3, number = 3),
            ),
            lapsPlayers = listOf(
                // Lap 1
                DatabaseLapPlayer(lap_id = 1, player_id = 1, cards_left = 0, cards_on_table = 31),
                DatabaseLapPlayer(lap_id = 1, player_id = 2, cards_left = 4, cards_on_table = 28),
                DatabaseLapPlayer(lap_id = 1, player_id = 3, cards_left = 5, cards_on_table = 24),
                DatabaseLapPlayer(lap_id = 1, player_id = 4, cards_left = 7, cards_on_table = 10),
                // Lap 2
                DatabaseLapPlayer(lap_id = 2, player_id = 1, cards_left = 2, cards_on_table = 22),
                DatabaseLapPlayer(lap_id = 2, player_id = 2, cards_left = 0, cards_on_table = 28),
                DatabaseLapPlayer(lap_id = 2, player_id = 3, cards_left = 3, cards_on_table = 26),
                DatabaseLapPlayer(lap_id = 2, player_id = 4, cards_left = 8, cards_on_table = 2),
                // Lap 3
                DatabaseLapPlayer(lap_id = 3, player_id = 1, cards_left = 1, cards_on_table = 18),
                DatabaseLapPlayer(lap_id = 3, player_id = 2, cards_left = 0, cards_on_table = 26),
                DatabaseLapPlayer(lap_id = 3, player_id = 3, cards_left = 4, cards_on_table = 30),
                DatabaseLapPlayer(lap_id = 3, player_id = 4, cards_left = 9, cards_on_table = 4),
            ),
            players = playersDto,
        )

        val expectedPlayers = listOf(
            Player(id = 1, name = "Andreo"),
            Player(id = 2, name = "Olenkka"),
            Player(id = 3, name = "Alina"),
            Player(id = 4, name = "Mario"),
        )
        val expected = Game(
            id = GameId(1),
            players = expectedPlayers,
            timeStarted = timeStarted,
            scores = mapOf(
                expectedPlayers[0] to Score(65),
                expectedPlayers[1] to Score(74),
                expectedPlayers[2] to Score(56),
                expectedPlayers[3] to Score(-32),
            ),
            completedLaps = listOf(
                Lap(
                    id = LapId(1),
                    number = 1,
                    cardsLeft = mapOf(
                        expectedPlayers[0] to 0,
                        expectedPlayers[1] to 4,
                        expectedPlayers[2] to 5,
                        expectedPlayers[3] to 7,
                    ),
                    cardsOnTable = mapOf(
                        expectedPlayers[0] to 31,
                        expectedPlayers[1] to 28,
                        expectedPlayers[2] to 24,
                        expectedPlayers[3] to 10,
                    ),
                ),
                Lap(
                    id = LapId(2),
                    number = 2,
                    cardsLeft = mapOf(
                        expectedPlayers[0] to 2,
                        expectedPlayers[1] to 0,
                        expectedPlayers[2] to 3,
                        expectedPlayers[3] to 8,
                    ),
                    cardsOnTable = mapOf(
                        expectedPlayers[0] to 22,
                        expectedPlayers[1] to 28,
                        expectedPlayers[2] to 26,
                        expectedPlayers[3] to 2,
                    ),
                ),
                Lap(
                    id = LapId(3),
                    number = 3,
                    cardsLeft = mapOf(
                        expectedPlayers[0] to 1,
                        expectedPlayers[1] to 0,
                        expectedPlayers[2] to 4,
                        expectedPlayers[3] to 9,
                    ),
                    cardsOnTable = mapOf(
                        expectedPlayers[0] to 18,
                        expectedPlayers[1] to 26,
                        expectedPlayers[2] to 30,
                        expectedPlayers[3] to 4,
                    ),
                ),
            ),
            endConditions = GameEndConditions(
                score = GameEndScoreCondition(
                    targetScore = Score(153)
                ),
                time = GameEndTimeCondition(
                    gameDuration = 2.hours + 34.minutes,
                    clock = clock,
                ),
            ),
        )

        val actual = mapper.map(
            game = gameDto,
            data = data,
        )

        assertEquals(expected, actual)
    }
}
