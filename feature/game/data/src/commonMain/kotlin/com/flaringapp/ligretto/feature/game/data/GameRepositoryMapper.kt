package com.flaringapp.ligretto.feature.game.data

import com.flaringapp.ligretto.feature.game.data.storage.GameDataStorageDto
import com.flaringapp.ligretto.feature.game.data.storage.StartGameStorageDto
import com.flaringapp.ligretto.feature.game.model.Game
import com.flaringapp.ligretto.feature.game.model.GameConfig
import com.flaringapp.ligretto.feature.game.model.GameId
import com.flaringapp.ligretto.feature.game.model.Player
import com.flaringapp.ligretto.feature.game.model.end.GameEndConditions
import com.flaringapp.ligretto.feature.game.model.end.GameEndScoreCondition
import com.flaringapp.ligretto.feature.game.model.end.GameEndTimeCondition
import org.koin.core.annotation.Factory
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import com.flaringapp.ligretto.core.database.Game as DatabaseGame

internal interface GameRepositoryMapper {

    fun mapNewGame(config: GameConfig, dto: StartGameStorageDto): Game

    fun mapGame(game: DatabaseGame, data: GameDataStorageDto): Game
}

@Factory
internal class GameRepositoryMapperImpl(
    private val loadGameMapper: LoadGameRepositoryMapper,
    private val clock: Clock,
) : GameRepositoryMapper {

    override fun mapNewGame(config: GameConfig, dto: StartGameStorageDto): Game {
        val players = config.players.mapIndexed { index, player ->
            Player(
                id = dto.playerIds[index],
                name = player.name,
            )
        }

        val endConditions = GameEndConditions(
            score = config.targetScore?.let { GameEndScoreCondition(it) },
            time = config.timeLimit?.let { GameEndTimeCondition(it, clock) },
        )

        return Game(
            id = GameId(dto.id),
            players = players,
            timeStarted = Instant.fromEpochMilliseconds(dto.timeStarted),
            endConditions = endConditions,
        )
    }

    override fun mapGame(game: DatabaseGame, data: GameDataStorageDto): Game {
        return loadGameMapper.map(
            game = game,
            data = data,
        )
    }
}
