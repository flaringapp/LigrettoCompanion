package com.flaringapp.ligretto.feature.game.ui.lap.cardsontable

import com.flaringapp.ligretto.core.arch.UiIntent
import com.flaringapp.ligretto.core.ui.ext.UiList
import com.flaringapp.ligretto.feature.game.ui.lap.common.player.GameLapPlayerCardsState

internal sealed interface GameLapCardsOnTableIntent : UiIntent {

    data object Init : GameLapCardsOnTableIntent
    data class UpdatePlayerCards(
        val playerCards: UiList<GameLapPlayerCardsState>,
    ) : GameLapCardsOnTableIntent

    data class IncrementCards(
        val playerId: Long
    ) : GameLapCardsOnTableIntent

    data class DecrementCards(
        val playerId: Long
    ) : GameLapCardsOnTableIntent

    data object EndLap : GameLapCardsOnTableIntent
    data object HideEndLapConfirmation : GameLapCardsOnTableIntent
    data object EndLapConfirmed : GameLapCardsOnTableIntent
}
