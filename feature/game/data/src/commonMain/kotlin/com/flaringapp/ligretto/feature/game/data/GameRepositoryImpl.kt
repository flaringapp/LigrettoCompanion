package com.flaringapp.ligretto.feature.game.data

import com.flaringapp.ligretto.feature.game.data.cache.GameCache
import com.flaringapp.ligretto.feature.game.data.settings.GameSettings
import com.flaringapp.ligretto.feature.game.data.storage.GameStorageDataSource
import com.flaringapp.ligretto.feature.game.domain.contracts.GameRepository
import com.flaringapp.ligretto.feature.game.model.Game
import com.flaringapp.ligretto.feature.game.model.GameConfig
import com.flaringapp.ligretto.feature.game.model.GameId
import com.flaringapp.ligretto.feature.game.model.GameSnapshot
import com.flaringapp.ligretto.feature.game.model.Lap
import com.flaringapp.ligretto.feature.game.model.LapId
import com.flaringapp.ligretto.feature.game.model.Player
import com.flaringapp.ligretto.feature.game.model.Score
import org.koin.core.annotation.Single
import kotlin.time.Duration
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

@Single
internal class GameRepositoryImpl(
    private val gameStorageDataSource: GameStorageDataSource,
    private val gameSettings: GameSettings,
    private val gameCache: GameCache,
    private val gameObservables: GameObservables,
    private val mapper: GameRepositoryMapper,
) : GameRepository {

    override val currentGameFlow: StateFlow<Game?>
        get() = gameObservables.gameFlow.asStateFlow()

    override val currentLapFlow: StateFlow<Lap?>
        get() = gameObservables.lapFlow.asStateFlow()

    override val previousGameFlow: Flow<GameSnapshot?> = gameStorageDataSource.lastGameFlow
        .map { game ->
            if (game == null) return@map null

            val gameData = gameStorageDataSource.getGameData(game.id)
            mapper.mapGameSnapshot(
                game = game,
                data = gameData,
            )
        }.onEach {
            gameCache.previousGame = it
        }

    override fun getCachedPreviousGame(): GameSnapshot? {
        return gameCache.previousGame
    }

    override fun getActiveGameId(): GameId? {
        return gameSettings.activeGameId?.let(::GameId)
    }

    override fun observeActiveGameId(): Flow<GameId?> {
        return gameSettings.activeGameIdFlow.map { it?.let(::GameId) }
    }

    override suspend fun resumeGame(gameSnapshot: GameSnapshot) {
        gameSettings.activeGameId = gameSnapshot.game.id.value
        gameObservables.gameFlow.value = gameSnapshot.game
        gameObservables.lapFlow.value = gameSnapshot.activeLap
    }

    override suspend fun startGame(gameConfig: GameConfig): Game {
        val dto = gameStorageDataSource.startGame(gameConfig)

        val newGame = mapper.mapNewGame(
            config = gameConfig,
            dto = dto,
        )

        gameSettings.activeGameId = newGame.id.value
        gameObservables.gameFlow.value = newGame
        return newGame
    }

    override fun endGame(): Game? {
        gameSettings.activeGameId = null

        return currentGameFlow.value.also {
            gameObservables.lapFlow.value = null
            gameObservables.gameFlow.value = null
        }
    }

    override suspend fun changeGameSettings(
        targetScore: Score?,
        timeLimit: Duration?,
    ): Game {
        val game = requireNotNull(currentGameFlow.value)

        gameStorageDataSource.changeGameSettings(
            gameId = game.id,
            targetScore = targetScore,
            timeLimit = timeLimit,
        )

        // TODO rework - should be coming from DB
        val updatedGame = game.copy(
            endConditions = mapper.mapGameEndConditions(
                targetScore = targetScore,
                timeLimit = timeLimit,
            ),
        )

        gameObservables.gameFlow.value = updatedGame
        return updatedGame
    }

    override suspend fun startNextLap(): Lap {
        val game = requireNotNull(currentGameFlow.value)
        val lapNumber = game.pendingLapNumber

        val lapId = gameStorageDataSource.startNextLap(
            gameId = game.id,
            lapNumber = lapNumber,
        )

        val lap = Lap.empty(
            id = LapId(lapId),
            number = lapNumber,
            players = game.players,
        )

        gameObservables.lapFlow.value = lap
        return lap
    }

    override suspend fun updateLapPlayerCards(lap: Lap, player: Player) {
        gameStorageDataSource.updateLapPlayerCards(
            lapId = lap.id,
            playerId = player.id,
            cardsLeft = lap.cardsLeft[player] ?: 0,
            cardsOnTable = lap.cardsOnTable[player] ?: 0,
        )

        gameObservables.lapFlow.value = lap
    }

    override suspend fun endLap(gameWithLap: Game) {
        val playerScores = gameWithLap.scores.mapKeys { it.key.id }

        gameStorageDataSource.endLap(
            gameId = gameWithLap.id,
            lapId = gameWithLap.lastLap!!.id,
            playerScores = playerScores,
        )

        gameObservables.gameFlow.value = gameWithLap
        gameObservables.lapFlow.value = null
    }
}
