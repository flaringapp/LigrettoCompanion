package com.flaringapp.ligretto.feature.game.data

import com.flaringapp.ligretto.feature.game.data.storage.GameStorageDataSource
import com.flaringapp.ligretto.feature.game.domain.contracts.GameRepository
import com.flaringapp.ligretto.feature.game.model.Game
import com.flaringapp.ligretto.feature.game.model.GameConfig
import com.flaringapp.ligretto.feature.game.model.Lap
import com.flaringapp.ligretto.feature.game.model.LapId
import org.koin.core.annotation.Single
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@Single
internal class GameRepositoryImpl(
    private val gameStorage: GameStorage,
    private val gameStorageDataSource: GameStorageDataSource,
    private val mapper: GameRepositoryMapper,
) : GameRepository {

    override val currentGameFlow: StateFlow<Game?>
        get() = gameStorage.gameFlow.asStateFlow()

    override val currentLapFlow: StateFlow<Lap?>
        get() = gameStorage.lapFlow.asStateFlow()

    override val previousGame: StateFlow<Game?>
        get() = gameStorage.previousGame.asStateFlow()

    override suspend fun startGame(gameConfig: GameConfig): Game {
        val dto = gameStorageDataSource.startGame(gameConfig)

        val newGame = mapper.mapNewGame(
            config = gameConfig,
            dto = dto,
        )

        gameStorage.gameFlow.value = newGame
        return newGame
    }

    override fun endGame(): Game? {
        return currentGameFlow.value.also {
            gameStorage.lapFlow.value = null
            gameStorage.gameFlow.value = null
            gameStorage.previousGame.value = it
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

        gameStorage.lapFlow.value = lap
        return lap
    }

    override fun updateLapCards(lap: Lap) {
        gameStorage.lapFlow.value = lap
    }

    override suspend fun endLap(gameWithLap: Game) {
        gameStorageDataSource.endLap(
            gameId = gameWithLap.id,
            lapId = gameWithLap.lastLap!!.id,
            playerScores = gameWithLap.scores,
        )

        gameStorage.gameFlow.value = gameWithLap
        gameStorage.lapFlow.value = null
    }
}
