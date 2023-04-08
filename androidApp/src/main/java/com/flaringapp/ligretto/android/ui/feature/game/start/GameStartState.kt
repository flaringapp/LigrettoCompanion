package com.flaringapp.ligretto.android.ui.feature.game.start

import androidx.compose.runtime.Immutable
import com.flaringapp.ligretto.android.ui.mvi.UiState

data class GameStartState(
    val players: Players = Players(),
) : UiState {

    data class Players(
        val list: List<Player> = emptyList(),
        val playersIdCounter: Int = 0,
        val focusedPlayerId: Int? = null,
    )

    @Immutable
    data class Player(
        val id: Int,
        val name: String,
    )
}
