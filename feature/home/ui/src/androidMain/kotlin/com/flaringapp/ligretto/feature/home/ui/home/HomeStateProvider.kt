package com.flaringapp.ligretto.feature.home.ui.home

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

internal class HomeStateProvider : PreviewParameterProvider<HomeState> {

    override val values: Sequence<HomeState> = sequenceOf(
        HomeState(
            hasPreviousGame = true,
        ),
        HomeState(
            hasPreviousGame = false,
        ),
    )
}
