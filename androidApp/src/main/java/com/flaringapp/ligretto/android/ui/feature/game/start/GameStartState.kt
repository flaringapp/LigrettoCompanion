package com.flaringapp.ligretto.android.ui.feature.game.start

import androidx.compose.runtime.Immutable
import com.flaringapp.ligretto.android.ui.mvi.UiState

data class GameStartState(
    val isEmpty: Boolean = true,
    val players: List<Player> = emptyList(),
    val playersIdCounter: Int = 0,
    val focusedPlayerId: Int? = null,
) : UiState {

    @Immutable
    data class Player(
        val id: Int,
        val name: String,
    )
}
