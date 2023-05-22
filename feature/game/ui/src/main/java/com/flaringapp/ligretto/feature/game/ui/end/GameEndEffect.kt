package com.flaringapp.ligretto.feature.game.ui.end

import com.flaringapp.ligretto.core.arch.UiEffect

sealed interface GameEndEffect : UiEffect {
    object Close : GameEndEffect
}
