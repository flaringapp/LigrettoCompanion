package com.flaringapp.ligretto.feature.home.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.flaringapp.ligretto.core.designsystem.AppTheme
import com.flaringapp.ligretto.feature.home.ui.home.screen.HomeScreenContent

@Preview
@Composable
private fun Preview(
    @PreviewParameter(HomeStateProvider::class)
    state: HomeState,
) {
    AppTheme {
        HomeScreenContent(
            state = state,
            dispatch = {},
        )
    }
}
