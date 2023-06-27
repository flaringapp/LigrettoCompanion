package com.flaringapp.ligretto.feature.game.ui.lap

import com.flaringapp.ligretto.core.arch.UiIntent
import com.flaringapp.ligretto.feature.game.model.Lap

internal sealed interface GameLapIntent : UiIntent {

    object InitDataUpdates : GameLapIntent
    data class UpdateData(
        val lap: Lap,
        val playersCards: List<GameLapState.PlayerCards>,
    ) : GameLapIntent

    data class IncrementCardsLeft(val playerId: Int) : GameLapIntent
    data class DecrementCardsLeft(val playerId: Int) : GameLapIntent

    data class IncrementCardsOnTable(val playerId: Int) : GameLapIntent
    data class DecrementCardsOnTable(val playerId: Int) : GameLapIntent

    object EndLap : GameLapIntent
    object HideEndLapConfirmation : GameLapIntent
    object EndLapConfirmed : GameLapIntent
}
