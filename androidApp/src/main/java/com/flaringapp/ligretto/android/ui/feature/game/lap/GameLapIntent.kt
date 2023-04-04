package com.flaringapp.ligretto.android.ui.feature.game.lap

import com.flaringapp.ligretto.android.ui.mvi.UiIntent
import com.flaringapp.ligretto.model.Lap
import com.flaringapp.ligretto.model.Player

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
