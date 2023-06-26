package com.flaringapp.ligretto.feature.home.ui

import com.flaringapp.ligretto.core.arch.UiEffect

internal sealed interface HomeEffect : UiEffect {

    data class OpenStartGame(val restartLastGame: Boolean) : HomeEffect
}
