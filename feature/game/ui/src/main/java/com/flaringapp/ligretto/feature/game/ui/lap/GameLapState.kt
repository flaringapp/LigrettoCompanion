package com.flaringapp.ligretto.feature.game.ui.lap

import androidx.compose.runtime.Immutable
import com.flaringapp.ligretto.core.arch.UiState
import com.flaringapp.ligretto.feature.game.model.Player

data class GameLapState(
    val playersCards: List<PlayerCards> = emptyList(),
    val showConfirmEndLap: Boolean = false,
) : UiState {

    @Immutable
    data class PlayerCards(
        val player: Player,
        val score: Int,
        val cardsLeft: Int,
        val cardsOnTable: Int,
    )
}
