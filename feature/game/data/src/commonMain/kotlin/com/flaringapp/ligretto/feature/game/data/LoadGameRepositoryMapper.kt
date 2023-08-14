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
import org.koin.core.annotation.Factory
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import com.flaringapp.ligretto.core.database.Game as DatabaseGame
import com.flaringapp.ligretto.core.database.LapPlayer as DatabaseLapPlayer
import com.flaringapp.ligretto.core.database.Player as DatabasePlayer
import com.flaringapp.ligretto.core.database.SelectAllByGameId as DatabaseGamePlayer
import com.flaringapp.ligretto.core.database.SelectAllByGameIdNumberAscending as DatabaseLap

internal interface LoadGameRepositoryMapper {

    fun map(game: DatabaseGame, data: GameDataStorageDto): Game
}

@Factory
internal class LoadGameRepositoryMapperImpl(
    private val clock: Clock,
) : LoadGameRepositoryMapper {

    override fun map(game: DatabaseGame, data: GameDataStorageDto): Game {
        val players = mapPlayersByIds(data.players)

        val scores = mapPlayerScores(
            players = players,
            gamePlayers = data.gamePlayers,
        )

        val laps = mapGameLaps(
            players = players,
            lapsPlayers = data.lapsPlayers,
            laps = data.laps,
        )

        return Game(
            id = GameId(game.id),
            players = players.values.toList(),
            timeStarted = Instant.fromEpochMilliseconds(game.time_started),
            scores = scores,
            completedLaps = laps,
            endConditions = mapGameEndConditions(game),
        )
    }

    private fun mapPlayersByIds(
        players: List<DatabasePlayer>,
    ): Map<Long, Player> {
        return players.associateTo(LinkedHashMap()) { dto ->
            val player = Player(
                id = dto.id,
                name = dto.name,
            )
            dto.id to player
        }
    }

    private fun mapPlayerScores(
        players: Map<Long, Player>,
        gamePlayers: List<DatabaseGamePlayer>,
    ): Map<Player, Score> {
        return gamePlayers.associate { gamePlayer ->
            val player = players[gamePlayer.player_id]!!
            val score = Score(gamePlayer.score.toInt())
            player to score
        }
    }

    private fun mapGameLaps(
        players: Map<Long, Player>,
        lapsPlayers: List<DatabaseLapPlayer>,
        laps: List<DatabaseLap>,
    ): List<Lap> {
        val lapPlayersByLaps = lapsPlayers.groupBy { it.lap_id }

        return laps.map { lap ->
            val cardsLeft = mutableMapOf<Player, Int>()
            val cardsOnTable = mutableMapOf<Player, Int>()

            lapPlayersByLaps[lap.id]?.let { lapPlayers ->
                lapPlayers.forEach { lapPlayer ->
                    val player = players[lapPlayer.player_id]!!
                    cardsLeft[player] = lapPlayer.cards_left.toInt()
                    cardsOnTable[player] = lapPlayer.cards_on_table.toInt()
                }
            }

            Lap(
                id = LapId(lap.id),
                number = lap.number.toInt(),
                cardsLeft = cardsLeft,
                cardsOnTable = cardsOnTable,
            )
        }
    }

    private fun mapGameEndConditions(game: DatabaseGame): GameEndConditions {
        val scoreEndCondition = game.target_score?.let {
            GameEndScoreCondition(
                targetScore = Score(it.toInt()),
            )
        }

        val timeEndCondition = run {
            val hours = game.duration_hours?.hours ?: return@run null
            val minutes = game.duration_minutes?.minutes ?: return@run null

            GameEndTimeCondition(
                gameDuration = hours + minutes,
                clock = clock,
            )
        }

        return GameEndConditions(
            score = scoreEndCondition,
            time = timeEndCondition,
        )
    }
}
