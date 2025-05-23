package com.flaringapp.ligretto.feature.home.ui.home

import com.flaringapp.ligretto.core.arch.UiIntent

internal sealed interface HomeIntent : UiIntent {

    data class UpdateData(
        val state: HomeState,
    ) : HomeIntent

    object StartGame : HomeIntent

    object ContinueActiveGame : HomeIntent
}
