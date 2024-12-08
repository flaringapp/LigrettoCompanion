package com.flaringapp.ligretto.feature.game.ui.start.screen.endconditions.settings

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.flaringapp.ligretto.core.ui.ext.UiList
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.GameEndConditionScoreIntent
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.GameEndConditionScoreLimitState
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.GameEndConditionTimeIntent
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.GameEndConditionTimeLimitState
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.ui.options.OptionPill
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.ui.options.OptionsRow
import com.flaringapp.ligretto.feature.game.ui.start.screen.endconditions.GameStartEndConditionsScope
import ligretto_companion.feature.game.ui.generated.resources.Res
import ligretto_companion.feature.game.ui.generated.resources.start_end_condition_option_custom
import ligretto_companion.feature.game.ui.generated.resources.start_end_condition_option_custom_with_value
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun GameStartEndConditionsScope.ScoreOptions(
    state: GameEndConditionScoreLimitState,
    dispatch: (GameEndConditionScoreIntent) -> Unit,
    modifier: Modifier = Modifier,
) {
    SpecificOptionsRow(
        modifier = modifier,
        staticValueOptions = state.staticOptions,
        selectedValue = state.selectedScore,
        customValue = state.lastCustomScore,
        onValueSelect = { dispatch(GameEndConditionScoreIntent.ValueChange(it)) },
        onCustomValueSelect = { dispatch(GameEndConditionScoreIntent.SelectCustomValue) },
        formatValue = { it.toString() },
    )
}

@Composable
internal fun GameStartEndConditionsScope.TimeOptions(
    state: GameEndConditionTimeLimitState,
    dispatch: (GameEndConditionTimeIntent) -> Unit,
    modifier: Modifier = Modifier,
) {
    SpecificOptionsRow(
        modifier = modifier,
        staticValueOptions = state.staticOptions,
        selectedValue = state.selectedMinutes,
        customValue = state.lastCustomMinutes,
        onValueSelect = { dispatch(GameEndConditionTimeIntent.ValueChange(it)) },
        onCustomValueSelect = { dispatch(GameEndConditionTimeIntent.SelectCustomValue) },
        formatValue = { "${it}m" },
    )
}

@Composable
private inline fun <T> GameStartEndConditionsScope.SpecificOptionsRow(
    staticValueOptions: UiList<T>,
    selectedValue: T,
    customValue: T?,
    crossinline onValueSelect: (T) -> Unit,
    noinline onCustomValueSelect: () -> Unit,
    formatValue: @Composable (T) -> String,
    modifier: Modifier = Modifier,
) {
    OptionsRow(
        modifier = modifier,
    ) {
        staticValueOptions.forEach { value ->
            OptionPill(
                text = formatValue(value),
                selected = value == selectedValue,
                onClick = { onValueSelect(value) },
            )
        }

        CustomValueOption(
            value = customValue?.let { formatValue(it) },
            selected = customValue == selectedValue,
            onClick = onCustomValueSelect,
        )
    }
}

@Composable
private fun GameStartEndConditionsScope.CustomValueOption(
    value: String?,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val text = value?.let {
        stringResource(Res.string.start_end_condition_option_custom_with_value, it)
    } ?: run {
        stringResource(Res.string.start_end_condition_option_custom)
    }

    OptionPill(
        modifier = modifier,
        text = text,
        selected = selected,
        onClick = onClick,
    )
}
