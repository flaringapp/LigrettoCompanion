package com.flaringapp.ligretto.android.ui.feature.game.start

import com.flaringapp.ligretto.core.arch.UiEffect

sealed interface GameStartEffect : UiEffect {
    object StartGame : GameStartEffect
}
