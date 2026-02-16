package com.flaringapp.ligretto.feature.home.ui.home.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.flaringapp.ligretto.feature.home.ui.home.HomeState

internal class HomeStateProvider : PreviewParameterProvider<HomeState> {

    override val values: Sequence<HomeState> = sequenceOf(
        HomeState(),
        HomeState(
            hasActiveGame = true,
        ),
    )
}
