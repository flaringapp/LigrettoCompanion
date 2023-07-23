package com.flaringapp.ligretto.feature.game.data.storage

import com.flaringapp.ligretto.core.database.Database
import com.flaringapp.ligretto.feature.game.model.GameConfig
import org.koin.core.annotation.Single
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlinx.datetime.Clock

internal interface GameStorageDataSource {

    suspend fun startGame(gameConfig: GameConfig): StartGameStorageDto
}

@Single
internal class GameStorageDataSourceImpl(
    private val database: Database,
    private val clock: Clock,
) : GameStorageDataSource {

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
}
