package com.flaringapp.ligretto.android.ui.feature.game.end

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.flaringapp.ligretto.android.ui.utils.navigation.ScreenDestinationWithoutArguments

object GameEndDestination : ScreenDestinationWithoutArguments() {

    override val screenId: String = "game/end"
}

@Composable
fun GameEndScreen(
    closeGame: () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Button(onClick = closeGame) {
            Text(text = "Close game")
        }
    }
}
