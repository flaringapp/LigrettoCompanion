package com.flaringapp.ligretto.feature.game.ui.end

import com.flaringapp.ligretto.core.arch.UiEffect

internal sealed interface GameEndEffect : UiEffect {
    object Close : GameEndEffect
}
