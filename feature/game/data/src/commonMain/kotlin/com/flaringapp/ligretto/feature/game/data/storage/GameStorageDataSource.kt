package com.flaringapp.ligretto.feature.game.data.storage

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToOneOrNull
import com.flaringapp.ligretto.core.database.Database
import com.flaringapp.ligretto.feature.game.model.GameConfig
import com.flaringapp.ligretto.feature.game.model.GameId
import com.flaringapp.ligretto.feature.game.model.LapId
import com.flaringapp.ligretto.feature.game.model.Player
import com.flaringapp.ligretto.feature.game.model.Score
import org.koin.core.annotation.Single
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.Clock
import com.flaringapp.ligretto.core.database.Game as DatabaseGame

internal interface GameStorageDataSource {

    val lastGameFlow: Flow<DatabaseGame?>

    suspend fun getGameData(gameId: Long): GameDataStorageDto

    suspend fun startGame(gameConfig: GameConfig): StartGameStorageDto

    suspend fun startNextLap(
        gameId: GameId,
        lapNumber: Int,
        playerIds: Iterable<Long>,
    ): Long

    suspend fun updateLapPlayerCards(
        lapId: LapId,
        playerId: Long,
        cardsLeft: Int,
        cardsOnTable: Int,
    )

    suspend fun endLap(
        gameId: GameId,
        lapId: LapId,
        playerScores: Map<Player, Score>,
    )
}

@Single
internal class GameStorageDataSourceImpl(
    private val database: Database,
    private val clock: Clock,
) : GameStorageDataSource {

    override val lastGameFlow: Flow<DatabaseGame?> =
        database.gameQueries
            .selectLast()
            .asFlow()
            .mapToOneOrNull(Dispatchers.IO)

    override suspend fun getGameData(gameId: Long): GameDataStorageDto {
        return database.transactionWithResult {
            val gamePlayers = database.gamePlayerQueries
                .selectAllByGameId(gameId)
                .executeAsList()

            val gamePlayersIds = gamePlayers.map { it.player_id }

            val players = database.playerQueries
                .selectAllByIds(gamePlayersIds)
                .executeAsList()

            val laps = database.lapQueries
                .selectAllByGameIdNumberAscending(gameId)
                .executeAsList()

            val lapsIds = laps.map { it.id }

            val lapsPlayers = database.lapPlayerQueries
                .selectAllByLaps(lapsIds)
                .executeAsList()

            GameDataStorageDto(
                gamePlayers = gamePlayers,
                laps = laps,
                lapsPlayers = lapsPlayers,
                players = players,
            )
        }
    }

    override suspend fun startGame(gameConfig: GameConfig): StartGameStorageDto {
        val hoursToMinutes = gameConfig.timeLimit?.let {
            val hours = it.inWholeHours.hours
            val minutes = (it - hours).inWholeMinutes.minutes
            hours.inWholeHours to minutes.inWholeMinutes
        }

        val timeStarted = clock.now().toEpochMilliseconds()

        return database.transactionWithResult {
            database.gameQueries.insert(
                time_started = timeStarted,
                target_score = gameConfig.targetScore?.value?.toLong(),
                duration_hours = hoursToMinutes?.first,
                duration_minutes = hoursToMinutes?.second,
            )
            val gameId = database.gameQueries.rowid().executeAsOne()

            val playerIds = gameConfig.players.map { player ->
                // Find existing player
                database.playerQueries
                    .selectIdByName(player.name)
                    .executeAsOneOrNull()
                    ?.let { return@map it }

                database.playerQueries.insert(name = player.name)
                database.playerQueries.rowid().executeAsOne()
            }

            playerIds.forEach { playerId ->
                database.gamePlayerQueries.insert(
                    game_id = gameId,
                    player_id = playerId,
                )
            }

            StartGameStorageDto(
                id = gameId,
                timeStarted = timeStarted,
                playerIds = playerIds,
            )
        }
    }

    override suspend fun startNextLap(
        gameId: GameId,
        lapNumber: Int,
        playerIds: Iterable<Long>,
    ): Long {
        return database.transactionWithResult {
            database.lapQueries.insert(
                game_id = gameId.value,
                number = lapNumber.toLong(),
            )
            val lapId = database.lapQueries.rowid().executeAsOne()

            playerIds.forEach { playerId ->
                database.lapPlayerQueries.insert(
                    lap_id = lapId,
                    player_id = playerId,
                )
            }

            lapId
        }
    }

    override suspend fun updateLapPlayerCards(
        lapId: LapId,
        playerId: Long,
        cardsLeft: Int,
        cardsOnTable: Int,
    ) {
        database.lapPlayerQueries.updateCards(
            lap_id = lapId.value,
            player_id = playerId,
            cards_left = cardsLeft.toLong(),
            cards_on_table = cardsOnTable.toLong(),
        )
    }

    override suspend fun endLap(
        gameId: GameId,
        lapId: LapId,
        playerScores: Map<Player, Score>,
    ) {
        database.transaction {
            database.gameQueries.updateCompletedLapId(
                id = gameId.value,
                completed_lap_id = lapId.value,
            )

            playerScores.forEach { (player, score) ->
                database.gamePlayerQueries.updateScore(
                    game_id = gameId.value,
                    player_id = player.id,
                    score = score.value.toLong(),
                )
            }
        }
    }
}
