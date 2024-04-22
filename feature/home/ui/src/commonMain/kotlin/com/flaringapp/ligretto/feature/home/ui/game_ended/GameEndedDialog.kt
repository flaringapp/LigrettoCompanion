package com.flaringapp.ligretto.feature.home.ui.game_ended

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import ligretto_companion.core.ui_mp.generated.resources.ok
import ligretto_companion.feature.home.ui.generated.resources.Res
import ligretto_companion.feature.home.ui.generated.resources.game_ended_message
import ligretto_companion.feature.home.ui.generated.resources.game_ended_title
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import ligretto_companion.core.ui_mp.generated.resources.Res as CoreRes

@OptIn(ExperimentalResourceApi::class)
@Composable
internal fun GameEndedDialog(
    dismiss: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = dismiss,
        title = {
            Text(
                text = stringResource(Res.string.game_ended_title),
                style = MaterialTheme.typography.titleMedium,
            )
        },
        text = {
            Text(text = stringResource(Res.string.game_ended_message))
        },
        confirmButton = {
            TextButton(onClick = dismiss) {
                Text(text = stringResource(CoreRes.string.ok))
            }
        },
    )
}
