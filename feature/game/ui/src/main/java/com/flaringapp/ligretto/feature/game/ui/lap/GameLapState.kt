package com.flaringapp.ligretto.feature.game.ui.lap

import com.flaringapp.ligretto.core.arch.UiState
import com.flaringapp.ligretto.core.ui.ext.UiList
import com.flaringapp.ligretto.core.ui.ext.emptyUiList

internal data class GameLapState(
    val playersCards: UiList<PlayerCards> = emptyUiList(),
    val showConfirmEndLap: Boolean = false,
) : UiState {

    data class PlayerCards(
        val player: Player,
        val score: Int,
        val cardsLeft: Int,
        val cardsOnTable: Int,
    )

    data class Player(
        val id: Long,
        val name: String,
    )
}
