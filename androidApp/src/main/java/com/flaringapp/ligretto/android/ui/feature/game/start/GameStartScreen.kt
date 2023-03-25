package com.flaringapp.ligretto.android.ui.feature.game.start

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.flaringapp.ligretto.android.ui.utils.navigation.ScreenDestinationWithoutArguments

object GameStartDestination : ScreenDestinationWithoutArguments() {

    override val screenId: String = "game/start"
}

@Composable
fun GameStartScreen(
    openScores: () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Button(onClick = openScores) {
            Text(text = "Open scores")
        }
    }
}
