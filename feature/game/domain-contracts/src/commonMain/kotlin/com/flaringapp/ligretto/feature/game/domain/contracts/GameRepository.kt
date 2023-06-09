package com.flaringapp.ligretto.feature.game.domain.contracts

import com.flaringapp.ligretto.feature.game.model.Game
import com.flaringapp.ligretto.feature.game.model.Lap
import kotlinx.coroutines.flow.StateFlow

interface GameRepository {

    val currentGameFlow: StateFlow<Game?>
    val currentLapFlow: StateFlow<Lap?>

    val previousGame: StateFlow<Game?>

    fun startGame(game: Game): Game
    fun endGame(): Game?

    fun startLap(lap: Lap)
    fun updateLapCards(lap: Lap)
    fun endLap(gameWithLap: Game)
}
