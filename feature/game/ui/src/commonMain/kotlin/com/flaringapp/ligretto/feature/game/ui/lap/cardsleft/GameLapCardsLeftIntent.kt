package com.flaringapp.ligretto.feature.game.ui.lap.cardsleft

import com.flaringapp.ligretto.core.arch.UiIntent
import com.flaringapp.ligretto.core.ui.ext.UiList
import com.flaringapp.ligretto.feature.game.ui.lap.common.player.GameLapPlayerCardsState

internal sealed interface GameLapCardsLeftIntent : UiIntent {

    data object Init : GameLapCardsLeftIntent
    data class UpdatePlayerCards(
        val playerCards: UiList<GameLapPlayerCardsState>,
    ) : GameLapCardsLeftIntent

    data class IncrementCards(
        val playerId: Long,
    ) : GameLapCardsLeftIntent

    data class DecrementCards(
        val playerId: Long,
    ) : GameLapCardsLeftIntent

    data object OpenCardsOnTable : GameLapCardsLeftIntent
}
