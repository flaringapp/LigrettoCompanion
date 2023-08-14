package com.flaringapp.ligretto.feature.game.data.storage

import app.cash.sqldelight.async.coroutines.awaitAsList
import app.cash.sqldelight.async.coroutines.awaitAsOne
import app.cash.sqldelight.async.coroutines.awaitAsOneOrNull
import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToOneOrNull
import com.flaringapp.ligretto.core.database.Database
import com.flaringapp.ligretto.feature.game.model.GameConfig
import com.flaringapp.ligretto.feature.game.model.GameId
import com.flaringapp.ligretto.feature.game.model.LapId
import com.flaringapp.ligretto.feature.game.model.Score
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlinx.coroutines.CoroutineDispatcher
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
        playerScores: Map<Long, Score>,
    )
}

internal class GameStorageDataSourceImpl(
    private val database: Database,
    private val clock: Clock,
    dispatcher: CoroutineDispatcher,
) : GameStorageDataSource {

    override val lastGameFlow: Flow<DatabaseGame?> =
        database.gameQueries
            .selectLast()
            .asFlow()
            .mapToOneOrNull(dispatcher)

    override suspend fun getGameData(gameId: Long): GameDataStorageDto {
        return database.transactionWithResult {
            val gamePlayers = database.gamePlayerQueries
                .selectAllByGameId(gameId)
                .awaitAsList()

            val gamePlayersIds = gamePlayers.map { it.player_id }

            val players = database.playerQueries
                .selectAllByIds(gamePlayersIds)
                .awaitAsList()

            val laps = database.lapQueries
                .selectAllByGameIdNumberAscending(gameId)
                .awaitAsList()

            val lapsIds = laps.map { it.id }

            val lapsPlayers = database.lapPlayerQueries
                .selectAllByLaps(lapsIds)
                .awaitAsList()

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
            val gameId = database.gameQueries.rowid().awaitAsOne()

            val playerIds = gameConfig.players.map { player ->
                // Find existing player
                database.playerQueries
                    .selectIdByName(player.name)
                    .awaitAsOneOrNull()
                    ?.let { return@map it }

                database.playerQueries.insert(name = player.name)
                database.playerQueries.rowid().awaitAsOne()
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
    ): Long {
        return database.transactionWithResult {
            val gamePlayers = database.gamePlayerQueries
                .selectAllByGameId(gameId.value)
                .awaitAsList()

            database.lapQueries.insert(
                game_id = gameId.value,
                number = lapNumber.toLong(),
            )
            val lapId = database.lapQueries.rowid().awaitAsOne()

            gamePlayers.forEach { gamePlayer ->
                database.lapPlayerQueries.insert(
                    lap_id = lapId,
                    player_id = gamePlayer.player_id,
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
        playerScores: Map<Long, Score>,
    ) {
        database.transaction {
            database.gameQueries.updateCompletedLapId(
                id = gameId.value,
                completed_lap_id = lapId.value,
            )

            playerScores.forEach { (playerId, score) ->
                database.gamePlayerQueries.updateScore(
                    game_id = gameId.value,
                    player_id = playerId,
                    score = score.value.toLong(),
                )
            }
        }
    }
}
