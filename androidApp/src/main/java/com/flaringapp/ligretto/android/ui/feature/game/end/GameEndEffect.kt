package com.flaringapp.ligretto.android.ui.feature.game.end

import com.flaringapp.ligretto.android.ui.mvi.UiEffect

sealed interface GameEndEffect : UiEffect {
    object Close : GameEndEffect
}
