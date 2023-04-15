package com.flaringapp.ligretto.core.data

import com.flaringapp.ligretto.core.model.Game
import com.flaringapp.ligretto.core.model.Lap
import com.flaringapp.ligretto.domain.contracts.GameRepository
import org.koin.core.annotation.Single
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@Single
internal class GameRepositoryImpl(
    private val gameStorage: GameStorage,
    private val gameIdProvider: GameIdProvider,
) : GameRepository {

    override val currentGameFlow: StateFlow<Game?>
        get() = gameStorage.gameFlow.asStateFlow()

    override val currentLapFlow: StateFlow<Lap?>
        get() = gameStorage.lapFlow.asStateFlow()

    override fun startGame(game: Game): Game {
        val id = gameIdProvider.provide()
        val newGame = game.copy(id = id)

        gameStorage.gameFlow.value = newGame
        return newGame
    }

    override fun endGame(): Game? {
        return currentGameFlow.value.also {
            gameStorage.lapFlow.value = null
            gameStorage.gameFlow.value = null
        }
    }

    override fun startLap(lap: Lap) {
        gameStorage.lapFlow.value = lap
    }

    override fun updateLapCards(lap: Lap) {
        gameStorage.lapFlow.value = lap
    }

    override fun endLap(gameWithLap: Game) {
        gameStorage.gameFlow.value = gameWithLap
        gameStorage.lapFlow.value = null
    }
}
