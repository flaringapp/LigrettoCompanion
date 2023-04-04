package com.flaringapp.ligretto.android.ui.feature.game.lap

import androidx.compose.runtime.Immutable
import com.flaringapp.ligretto.android.ui.mvi.UiState
import com.flaringapp.ligretto.model.Player

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
