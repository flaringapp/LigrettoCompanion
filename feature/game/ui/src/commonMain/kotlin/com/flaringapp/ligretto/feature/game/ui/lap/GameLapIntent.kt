package com.flaringapp.ligretto.feature.game.ui.lap

import com.flaringapp.ligretto.core.arch.UiIntent
import com.flaringapp.ligretto.core.ui.ext.UiList

internal sealed interface GameLapIntent : UiIntent {

    object InitDataUpdates : GameLapIntent
    data class UpdateData(
        val playersCards: UiList<GameLapState.PlayerCards>,
    ) : GameLapIntent

    data class IncrementCardsLeft(val playerId: Long) : GameLapIntent
    data class DecrementCardsLeft(val playerId: Long) : GameLapIntent

    data class IncrementCardsOnTable(val playerId: Long) : GameLapIntent
    data class DecrementCardsOnTable(val playerId: Long) : GameLapIntent

    object EndLap : GameLapIntent
    object HideEndLapConfirmation : GameLapIntent
    object EndLapConfirmed : GameLapIntent
}
