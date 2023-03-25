package com.flaringapp.ligretto.android.ui.feature.game.score

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.flaringapp.ligretto.android.ui.utils.navigation.ScreenDestinationWithoutArguments

object GameScoreDestination : ScreenDestinationWithoutArguments() {

    override val screenId: String = "game/score"
}

@Composable
fun GameScoreScreen(
    openNextLap: () -> Unit,
    openEnd: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Button(onClick = openNextLap) {
            Text(text = "Next lap")
        }
        Button(onClick = openEnd) {
            Text(text = "End game")
        }
    }
}
