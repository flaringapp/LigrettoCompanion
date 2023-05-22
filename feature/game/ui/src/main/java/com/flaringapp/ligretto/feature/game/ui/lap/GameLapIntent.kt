package com.flaringapp.ligretto.feature.game.ui.lap

import com.flaringapp.ligretto.core.arch.UiIntent
import com.flaringapp.ligretto.feature.game.model.Lap
import com.flaringapp.ligretto.feature.game.model.Player

sealed interface GameLapIntent : UiIntent {

    object InitDataUpdates : GameLapIntent
    data class UpdateData(
        val lap: Lap,
        val playersCards: List<GameLapState.PlayerCards>,
    ) : GameLapIntent

    data class IncrementCardsLeft(val player: Player) : GameLapIntent
    data class DecrementCardsLeft(val player: Player) : GameLapIntent

    data class IncrementCardsOnTable(val player: Player) : GameLapIntent
    data class DecrementCardsOnTable(val player: Player) : GameLapIntent

    object EndLap : GameLapIntent
    object HideEndLapConfirmation : GameLapIntent
    object EndLapConfirmed : GameLapIntent
}