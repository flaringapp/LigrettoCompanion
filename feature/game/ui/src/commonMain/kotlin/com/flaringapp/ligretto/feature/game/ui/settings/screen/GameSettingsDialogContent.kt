package com.flaringapp.ligretto.feature.game.ui.settings.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.SettingsInputComposite
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.GameEndConditionScoreIntent
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.GameEndConditionScoreLimitState
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.GameEndConditionTimeIntent
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.GameEndConditionTimeLimitState
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.ui.options.ScoreOptions
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.ui.options.TimeOptions
import com.flaringapp.ligretto.feature.game.ui.settings.GameSettingsIntent
import com.flaringapp.ligretto.feature.game.ui.settings.GameSettingsState
import com.flaringapp.ligretto.feature.game.ui.settings.screen.endconditions.ConditionSettings
import com.flaringapp.ligretto.feature.game.ui.settings.screen.endconditions.GameSettingsEndConditionsScope
import com.flaringapp.ligretto.feature.game.ui.settings.screen.endconditions.LabeledOptions
import ligretto_companion.core.ui.generated.resources.cancel
import ligretto_companion.feature.game.ui.generated.resources.Res
import ligretto_companion.feature.game.ui.generated.resources.game_settings_save_button
import ligretto_companion.feature.game.ui.generated.resources.game_settings_score_end_condition_label
import ligretto_companion.feature.game.ui.generated.resources.game_settings_score_end_condition_name
import ligretto_companion.feature.game.ui.generated.resources.game_settings_time_end_condition_label
import ligretto_companion.feature.game.ui.generated.resources.game_settings_time_end_condition_name
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
                modifier = Modifier.padding(top = 8.dp),
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
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        GameSettingsEndConditionsScope.EndConditionsScoreContent(
            state = state.score,
            dispatch = dispatch,
        )

        GameSettingsEndConditionsScope.EndConditionsTimeContent(
            modifier = Modifier.padding(top = 16.dp),
            state = state.time,
            dispatch = dispatch,
        )
    }
}

@Composable
private fun GameSettingsEndConditionsScope.EndConditionsScoreContent(
    state: GameEndConditionScoreLimitState,
    dispatch: (GameSettingsIntent.EndConditions.Score) -> Unit,
    modifier: Modifier = Modifier,
) {
    ConditionSettings(
        modifier = modifier,
        conditionName = stringResource(Res.string.game_settings_score_end_condition_name),
        selected = state.isEnabled,
        onSelectionChanged = {
            val intent = GameEndConditionScoreIntent.SetEnabled(it)
            dispatch(GameSettingsIntent.EndConditions.Score(intent))
        },
    ) {
        LabeledOptions(
            label = stringResource(Res.string.game_settings_score_end_condition_label),
        ) {
            ScoreOptions(
                state = state,
                dispatch = {
                    dispatch(GameSettingsIntent.EndConditions.Score(it))
                },
            )
        }
    }
}

@Composable
private fun GameSettingsEndConditionsScope.EndConditionsTimeContent(
    state: GameEndConditionTimeLimitState,
    dispatch: (GameSettingsIntent.EndConditions.Time) -> Unit,
    modifier: Modifier = Modifier,
) {
    ConditionSettings(
        modifier = modifier,
        conditionName = stringResource(Res.string.game_settings_time_end_condition_name),
        selected = state.isEnabled,
        onSelectionChanged = {
            val intent = GameEndConditionTimeIntent.SetEnabled(it)
            dispatch(GameSettingsIntent.EndConditions.Time(intent))
        },
    ) {
        LabeledOptions(
            label = stringResource(Res.string.game_settings_time_end_condition_label),
        ) {
            TimeOptions(
                state = state,
                dispatch = {
                    dispatch(GameSettingsIntent.EndConditions.Time(it))
                },
            )
        }
    }
}
