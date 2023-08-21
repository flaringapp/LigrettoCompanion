package com.flaringapp.ligretto.feature.home.ui.game_ended

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.DialogProperties
import com.flaringapp.ligretto.core.navigation.DialogDestination
import com.flaringapp.ligretto.core.navigation.ScreenDestinationWithoutArguments
import com.flaringapp.ligretto.feature.home.ui.R
import com.flaringapp.ligretto.core.ui.R as CoreR

internal object GameEndedDestination : ScreenDestinationWithoutArguments(), DialogDestination {

    override val screenId: String = "home/game-ended"

    override val dialogProperties: DialogProperties = DialogProperties()
}

@Composable
internal fun GameEndedDialog(
    dismiss: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = dismiss,
        title = {
            Text(
                text = stringResource(R.string.game_ended_title),
                style = MaterialTheme.typography.titleMedium,
            )
        },
        text = {
            Text(text = stringResource(R.string.game_ended_message))
        },
        confirmButton = {
            TextButton(onClick = dismiss) {
                Text(text = stringResource(CoreR.string.ok))
            }
        },
    )
}
