package com.flaringapp.ligretto.feature.game.ui.common.endconditions.ui.custom

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Scoreboard
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.window.DialogProperties
import com.flaringapp.ligretto.core.ui.ext.rememberDefaultTextFieldValueToStringInterop
import com.flaringapp.ligretto.core.util.common.getDigits
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.GameEndConditionScoreIntent
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.GameEndConditionScoreLimitState
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.GameEndConditionTimeIntent
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.GameEndConditionTimeLimitState
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.ui.GameEndConditionsScope
import ligretto_companion.core.ui.generated.resources.cancel
import ligretto_companion.feature.game.ui.generated.resources.Res
import ligretto_companion.feature.game.ui.generated.resources.game_settings_custom_score_label
import ligretto_companion.feature.game.ui.generated.resources.game_settings_custom_score_title
import ligretto_companion.feature.game.ui.generated.resources.game_settings_custom_time_label
import ligretto_companion.feature.game.ui.generated.resources.game_settings_custom_time_minutes_suffix
import ligretto_companion.feature.game.ui.generated.resources.game_settings_custom_time_title
import ligretto_companion.feature.game.ui.generated.resources.game_settings_custom_value_save_button
import org.jetbrains.compose.resources.stringResource
import ligretto_companion.core.ui.generated.resources.Res as CoreRes

@Composable
internal fun GameEndConditionsScope.CustomScoreInputDialog(
    state: GameEndConditionScoreLimitState.CustomInput,
    dispatch: (GameEndConditionScoreIntent) -> Unit,
) {
    val filterInput: (String) -> String = remember {
        { it.getDigits().take(4) }
    }

    CustomValueInputDialog(
        icon = Icons.Outlined.Scoreboard,
        title = stringResource(Res.string.game_settings_custom_score_title),
        label = stringResource(Res.string.game_settings_custom_score_label),
        value = state.value,
        onValueChange = {
            dispatch(GameEndConditionScoreIntent.NewCustomValueChange(it))
        },
        filterInput = filterInput,
        error = state.error?.resolve(),
        save = {
            dispatch(GameEndConditionScoreIntent.SubmitNewCustomValue)
        },
        dismiss = {
            dispatch(GameEndConditionScoreIntent.DismissNewCustomValue)
        },
    )
}

@Composable
internal fun GameEndConditionsScope.CustomTimeInputDialog(
    state: GameEndConditionTimeLimitState.CustomInput,
    dispatch: (GameEndConditionTimeIntent) -> Unit,
) {
    val filterInput: (String) -> String = remember {
        { it.getDigits().take(3) }
    }

    CustomValueInputDialog(
        icon = Icons.Outlined.Timer,
        title = stringResource(Res.string.game_settings_custom_time_title),
        label = stringResource(Res.string.game_settings_custom_time_label),
        suffix = stringResource(Res.string.game_settings_custom_time_minutes_suffix),
        value = state.value,
        onValueChange = {
            dispatch(GameEndConditionTimeIntent.NewCustomValueChange(it))
        },
        filterInput = filterInput,
        error = state.error?.resolve(),
        save = {
            dispatch(GameEndConditionTimeIntent.SubmitNewCustomValue)
        },
        dismiss = {
            dispatch(GameEndConditionTimeIntent.DismissNewCustomValue)
        },
    )
}

@Composable
private fun GameEndConditionsScope.CustomValueInputDialog(
    icon: ImageVector,
    title: String,
    label: String,
    suffix: String? = null,
    value: String,
    onValueChange: (String) -> Unit,
    filterInput: (String) -> String,
    error: String?,
    save: () -> Unit,
    dismiss: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = dismiss,
        properties = DialogProperties(
            dismissOnClickOutside = false,
        ),
        icon = {
            Icon(
                imageVector = icon,
                contentDescription = null,
            )
        },
        title = {
            Text(text = title)
        },
        text = {
            val focusRequester = remember { FocusRequester() }

            val interop = rememberDefaultTextFieldValueToStringInterop(
                value = value,
                onValueChange = onValueChange,
                customizeValueOnChange = filterInput,
            )

            val suffixText = suffix?.let {
                @Composable { Text(text = it) }
            }
            val errorText = error?.let {
                @Composable { Text(text = it) }
            }

            LaunchedEffect(Unit) {
                focusRequester.requestFocus()
            }

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
                value = interop.value,
                onValueChange = interop.onValueChange,
                label = {
                    Text(text = label)
                },
                suffix = suffixText,
                supportingText = errorText,
                isError = errorText != null,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done,
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        save()
                    },
                ),
                singleLine = true,
            )
        },
        confirmButton = {
            TextButton(onClick = save) {
                Text(text = stringResource(Res.string.game_settings_custom_value_save_button))
            }
        },
        dismissButton = {
            TextButton(onClick = dismiss) {
                Text(text = stringResource(CoreRes.string.cancel))
            }
        },
    )
}
