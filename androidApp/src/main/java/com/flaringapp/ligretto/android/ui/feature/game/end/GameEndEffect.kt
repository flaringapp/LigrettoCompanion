package com.flaringapp.ligretto.android.ui.feature.game.end

import com.flaringapp.ligretto.core.arch.UiEffect

sealed interface GameEndEffect : UiEffect {
    object Close : GameEndEffect
}
