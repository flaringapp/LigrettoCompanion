package com.flaringapp.ligretto.feature.home.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.flaringapp.ligretto.core.navigation.ScreenDestinationWithoutArguments

internal object HomeScreenDestination : ScreenDestinationWithoutArguments() {

    override val screenId: String = "home/main"
}

@Composable
internal fun HomeScreen(
    startGame: () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Button(onClick = startGame) {
            Text(text = "Start game")
        }
    }
}
