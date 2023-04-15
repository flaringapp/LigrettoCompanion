package com.flaringapp.ligretto.domain.contracts

import com.flaringapp.ligretto.core.model.Game
import com.flaringapp.ligretto.core.model.Lap
import kotlinx.coroutines.flow.StateFlow

interface GameRepository {

    val currentGameFlow: StateFlow<Game?>
    val currentLapFlow: StateFlow<Lap?>

    fun setCurrentGame(game: Game?)
    fun setCurrentLap(lap: Lap?)

}
