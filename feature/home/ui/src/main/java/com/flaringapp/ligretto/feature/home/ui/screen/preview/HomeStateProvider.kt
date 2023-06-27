package com.flaringapp.ligretto.feature.home.ui.screen.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.flaringapp.ligretto.feature.home.ui.HomeState

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
