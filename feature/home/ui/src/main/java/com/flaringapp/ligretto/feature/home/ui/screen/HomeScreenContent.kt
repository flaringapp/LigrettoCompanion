package com.flaringapp.ligretto.feature.home.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.flaringapp.ligretto.core.designsystem.AppTheme
import com.flaringapp.ligretto.feature.home.ui.HomeIntent
import com.flaringapp.ligretto.feature.home.ui.HomeState
import com.flaringapp.ligretto.feature.home.ui.screen.preview.HomeStateProvider

@Composable
internal fun HomeScreenContent(
    state: HomeState,
    dispatch: (HomeIntent) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(
            onClick = { dispatch(HomeIntent.StartNewGame) },
        ) {
            Text(text = "Start game")
        }

        if (state.hasPreviousGame) {
            TextButton(
                modifier = Modifier.padding(top = 32.dp),
                onClick = { dispatch(HomeIntent.RestartLastGame) },
            ) {
                Text(text = "Restart last game")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview(
    @PreviewParameter(HomeStateProvider::class)
    state: HomeState
) {
    AppTheme {
        HomeScreenContent(
            state = state,
            dispatch = {},
        )
    }
}
