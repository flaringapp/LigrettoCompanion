@file:OptIn(ExperimentalResourceApi::class)

package com.flaringapp.ligretto.feature.game.ui.lap.screen

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import com.flaringapp.ligretto.core.designsystem.AppTheme
import ligretto_companion.core.ui_mp.generated.resources.cancel
import ligretto_companion.core.ui_mp.generated.resources.yes
import ligretto_companion.feature.game.ui.generated.resources.Res
import ligretto_companion.feature.game.ui.generated.resources.lap_end_lap_confirmation_message
import ligretto_companion.feature.game.ui.generated.resources.lap_end_lap_confirmation_title
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import ligretto_companion.core.ui_mp.generated.resources.Res as CoreRes

@Composable
internal fun GameLapEndLapDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = stringResource(Res.string.lap_end_lap_confirmation_title),
                style = MaterialTheme.typography.titleMedium,
            )
        },
        text = {
            Text(text = stringResource(Res.string.lap_end_lap_confirmation_message))
        },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text(text = stringResource(CoreRes.string.yes))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = stringResource(CoreRes.string.cancel))
            }
        }
    )
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        GameLapEndLapDialog(
            onConfirm = {},
            onDismiss = {},
        )
    }
}
