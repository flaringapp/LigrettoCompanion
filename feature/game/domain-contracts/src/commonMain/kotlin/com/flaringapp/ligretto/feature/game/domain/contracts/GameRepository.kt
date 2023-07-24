package com.flaringapp.ligretto.feature.game.domain.contracts

import com.flaringapp.ligretto.feature.game.model.Game
import com.flaringapp.ligretto.feature.game.model.GameConfig
import com.flaringapp.ligretto.feature.game.model.Lap
import kotlinx.coroutines.flow.StateFlow

interface GameRepository {

    val currentGameFlow: StateFlow<Game?>
    val currentLapFlow: StateFlow<Lap?>

    val previousGame: StateFlow<Game?>

    suspend fun startGame(gameConfig: GameConfig): Game
    fun endGame(): Game?

    suspend fun startNextLap(): Lap
    fun updateLapCards(lap: Lap)
    fun endLap(gameWithLap: Game)
}
