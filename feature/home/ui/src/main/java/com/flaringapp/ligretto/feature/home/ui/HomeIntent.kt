package com.flaringapp.ligretto.feature.home.ui

import com.flaringapp.ligretto.core.arch.UiIntent

internal sealed interface HomeIntent : UiIntent {

    data class UpdateData(
        val state: HomeState,
    ) : HomeIntent

    object StartNewGame : HomeIntent

    object RestartLastGame : HomeIntent

    object ContinueActiveGame : HomeIntent
}
