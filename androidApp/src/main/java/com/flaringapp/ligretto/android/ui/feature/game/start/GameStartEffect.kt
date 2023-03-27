package com.flaringapp.ligretto.android.ui.feature.game.start

import com.flaringapp.ligretto.android.ui.mvi.UiEffect

sealed interface GameStartEffect : UiEffect {
    object StartGame : GameStartEffect
}
