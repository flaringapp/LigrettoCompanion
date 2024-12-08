package com.flaringapp.ligretto.feature.game.ui.settings.screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.SettingsInputComposite
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.flaringapp.ligretto.feature.game.ui.settings.GameSettingsIntent
import com.flaringapp.ligretto.feature.game.ui.settings.GameSettingsState
import ligretto_companion.core.ui.generated.resources.cancel
import ligretto_companion.feature.game.ui.generated.resources.Res
import ligretto_companion.feature.game.ui.generated.resources.game_settings_save_button
import ligretto_companion.feature.game.ui.generated.resources.game_settings_title
import org.jetbrains.compose.resources.stringResource
import ligretto_companion.core.ui.generated.resources.Res as CoreRes

@Composable
internal fun GameSettingsDialogContent(
    state: GameSettingsState,
    dispatch: (GameSettingsIntent) -> Unit,
    close: () -> Unit,
) {
    AlertDialog(
        modifier = Modifier.fillMaxWidth(),
        onDismissRequest = close,
        icon = {
            Icon(
                imageVector = Icons.Outlined.SettingsInputComposite,
                contentDescription = null,
            )
        },
        title = {
            Text(text = stringResource(Res.string.game_settings_title))
        },
        text = {
            ActualContent(
                state = state,
                dispatch = dispatch,
            )
        },
        confirmButton = {
            TextButton(
                onClick = { dispatch(GameSettingsIntent.Save) },
            ) {
                Text(text = stringResource(Res.string.game_settings_save_button))
            }
        },
        dismissButton = {
            TextButton(onClick = close) {
                Text(text = stringResource(CoreRes.string.cancel))
            }
        },
    )
}

@Composable
private fun ActualContent(
    state: GameSettingsState,
    dispatch: (GameSettingsIntent) -> Unit,
) {
}
