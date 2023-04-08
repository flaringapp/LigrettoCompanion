package com.flaringapp.ligretto.android.ui.feature.game.start

import com.flaringapp.ligretto.android.ui.mvi.UiIntent

sealed interface GameStartIntent : UiIntent {

    object StartGame : GameStartIntent
}

sealed interface GameStartPlayersIntent : GameStartIntent {

    object AddNew : GameStartPlayersIntent

    data class ChangeName(
        val id: Int,
        val name: String,
    ) : GameStartPlayersIntent

    data class FocusChanged(
        val id: Int,
        val isFocused: Boolean,
    ) : GameStartPlayersIntent

    data class Remove(val id: Int) : GameStartPlayersIntent
}
