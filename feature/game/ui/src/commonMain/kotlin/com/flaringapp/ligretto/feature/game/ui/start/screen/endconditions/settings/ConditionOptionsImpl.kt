package com.flaringapp.ligretto.feature.game.ui.start.screen.endconditions.settings

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.flaringapp.ligretto.core.ui.ext.UiList
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.options.OptionPill
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.options.OptionsRow
import com.flaringapp.ligretto.feature.game.ui.start.GameStartScoreEndConditionIntent
import com.flaringapp.ligretto.feature.game.ui.start.GameStartState.EndConditions.ScoreLimit
import com.flaringapp.ligretto.feature.game.ui.start.GameStartState.EndConditions.TimeLimit
import com.flaringapp.ligretto.feature.game.ui.start.GameStartTimeEndConditionIntent
import com.flaringapp.ligretto.feature.game.ui.start.screen.endconditions.GameStartEndConditionsScope
import ligretto_companion.feature.game.ui.generated.resources.Res
import ligretto_companion.feature.game.ui.generated.resources.start_end_condition_option_custom
import ligretto_companion.feature.game.ui.generated.resources.start_end_condition_option_custom_with_value
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun GameStartEndConditionsScope.ScoreOptions(
    state: ScoreLimit,
    dispatch: (GameStartScoreEndConditionIntent) -> Unit,
    modifier: Modifier = Modifier,
) {
    SpecificOptionsRow(
        modifier = modifier,
        staticValueOptions = state.staticOptions,
        selectedValue = state.selectedScore,
        customValue = state.lastCustomScore,
        onValueSelect = { dispatch(GameStartScoreEndConditionIntent.ValueChange(it)) },
        onCustomValueSelect = { dispatch(GameStartScoreEndConditionIntent.SelectCustomValue) },
        formatValue = { it.toString() },
    )
}

@Composable
internal fun GameStartEndConditionsScope.TimeOptions(
    state: TimeLimit,
    dispatch: (GameStartTimeEndConditionIntent) -> Unit,
    modifier: Modifier = Modifier,
) {
    SpecificOptionsRow(
        modifier = modifier,
        staticValueOptions = state.staticOptions,
        selectedValue = state.selectedMinutes,
        customValue = state.lastCustomMinutes,
        onValueSelect = { dispatch(GameStartTimeEndConditionIntent.ValueChange(it)) },
        onCustomValueSelect = { dispatch(GameStartTimeEndConditionIntent.SelectCustomValue) },
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
