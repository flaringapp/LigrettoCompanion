package com.flaringapp.ligretto.feature.game.data

import com.flaringapp.ligretto.feature.game.data.cache.GameCache
import com.flaringapp.ligretto.feature.game.data.settings.GameSettings
import com.flaringapp.ligretto.feature.game.data.storage.GameStorageDataSource
import com.flaringapp.ligretto.feature.game.domain.contracts.GameRepository
import com.flaringapp.ligretto.feature.game.model.Game
import com.flaringapp.ligretto.feature.game.model.GameConfig
import com.flaringapp.ligretto.feature.game.model.Lap
import com.flaringapp.ligretto.feature.game.model.LapId
import com.flaringapp.ligretto.feature.game.model.Player
import org.koin.core.annotation.Single
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

    override val previousGameFlow: Flow<Game?>
        get() = gameStorageDataSource.lastGameFlow.map { game ->
            if (game == null) return@map null

            val gameData = gameStorageDataSource.getGameData(game.id)
            mapper.mapGame(
                game = game,
                data = gameData,
            )
        }.onEach {
            gameCache.previousGame = it
        }

    override fun getCachedPreviousGame(): Game? {
        return gameCache.previousGame
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

    override suspend fun startNextLap(): Lap {
        val game = requireNotNull(currentGameFlow.value)
        val lapNumber = game.pendingLapNumber
        val playerIds = game.players
            .asSequence()
            .map { it.id }
            .asIterable()

        val lapId = gameStorageDataSource.startNextLap(
            gameId = game.id,
            lapNumber = lapNumber,
            playerIds = playerIds,
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
        gameStorageDataSource.endLap(
            gameId = gameWithLap.id,
            lapId = gameWithLap.lastLap!!.id,
            playerScores = gameWithLap.scores,
        )

        gameObservables.gameFlow.value = gameWithLap
        gameObservables.lapFlow.value = null
    }
}
