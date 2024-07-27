package com.flaringapp.ligretto.feature.home.ui.home

import com.flaringapp.ligretto.core.arch.UiEffect

internal sealed interface HomeEffect : UiEffect {

    data class OpenStartGame(
        val restartLastGame: Boolean
    ) : HomeEffect

    data class OpenResumeGame(
        val openLap: Boolean
    ) : HomeEffect

    object OpenActiveGameEnded : HomeEffect
}
