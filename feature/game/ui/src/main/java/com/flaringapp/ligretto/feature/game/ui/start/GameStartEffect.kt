package com.flaringapp.ligretto.feature.game.ui.start

import com.flaringapp.ligretto.core.arch.UiEffect

sealed interface GameStartEffect : UiEffect {
    object StartGame : GameStartEffect
}
