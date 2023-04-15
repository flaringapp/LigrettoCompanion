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
) : GameRepository {

    override val currentGameFlow: StateFlow<Game?>
        get() = gameStorage.gameFlow.asStateFlow()

    override val currentLapFlow: StateFlow<Lap?>
        get() = gameStorage.lapFlow.asStateFlow()

    override fun setCurrentGame(game: Game?) {
        gameStorage.gameFlow.value = game
    }

    override fun setCurrentLap(lap: Lap?) {
        gameStorage.lapFlow.value = lap
    }
}
