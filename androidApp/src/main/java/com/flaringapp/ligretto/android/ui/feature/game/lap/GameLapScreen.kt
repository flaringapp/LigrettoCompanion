package com.flaringapp.ligretto.android.ui.feature.game.lap

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.flaringapp.ligretto.android.ui.utils.navigation.ScreenDestinationWithoutArguments

object GameLapDestination : ScreenDestinationWithoutArguments() {

    override val screenId: String = "game/lap"
}

@Composable
fun GameLapScreen(
    openScores: () -> Unit,
    openEnd: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Button(onClick = openScores) {
            Text(text = "End lap")
        }
        Button(onClick = openEnd) {
            Text(text = "End game")
        }
    }
}
