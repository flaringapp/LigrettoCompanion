package com.flaringapp.ligretto.android.ui.feature.game.start

import com.flaringapp.ligretto.android.ui.mvi.UiIntent

sealed interface GameStartIntent : UiIntent {

    object AddNewPlayer : GameStartIntent

    data class ChangePlayerName(
        val id: Int,
        val name: String,
    ) : GameStartIntent

    data class PlayerFocusChanged(
        val id: Int,
        val isFocused: Boolean,
    ) : GameStartIntent

    data class RemovePlayer(val id: Int) : GameStartIntent

    object StartGame : GameStartIntent
}
