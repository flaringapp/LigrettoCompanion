package com.flaringapp.ligretto.domain.contracts

import com.flaringapp.ligretto.core.model.Game
import com.flaringapp.ligretto.core.model.Lap
import kotlinx.coroutines.flow.StateFlow

interface GameRepository {

    val currentGameFlow: StateFlow<Game?>
    val currentLapFlow: StateFlow<Lap?>

    fun startGame(game: Game): Game
    fun endGame(): Game?

    fun startLap(lap: Lap)
    fun updateLapCards(lap: Lap)
    fun endLap(gameWithLap: Game)

}
