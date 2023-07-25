package com.flaringapp.ligretto.feature.game.domain.contracts

import com.flaringapp.ligretto.feature.game.model.Game
import com.flaringapp.ligretto.feature.game.model.GameConfig
import com.flaringapp.ligretto.feature.game.model.Lap
import com.flaringapp.ligretto.feature.game.model.Player
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface GameRepository {

    val currentGameFlow: StateFlow<Game?>
    val currentLapFlow: StateFlow<Lap?>

    val previousGame: Flow<Game?>

    suspend fun startGame(gameConfig: GameConfig): Game
    fun endGame(): Game?

    suspend fun startNextLap(): Lap

    suspend fun updateLapPlayerCards(lap: Lap, player: Player)

    suspend fun endLap(gameWithLap: Game)
}
