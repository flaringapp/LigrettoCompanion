package com.flaringapp.ligretto.feature.game.ui.common.endconditions.ui.options

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.flaringapp.ligretto.core.ui.ext.UiList
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.GameEndConditionScoreIntent
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.GameEndConditionScoreLimitState
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.GameEndConditionTimeIntent
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.GameEndConditionTimeLimitState
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.ui.GameEndConditionsScope
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.ui.custom.CustomScoreInputDialog
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.ui.custom.CustomTimeInputDialog
import ligretto_companion.feature.game.ui.generated.resources.Res
import ligretto_companion.feature.game.ui.generated.resources.end_condition_option_custom
import ligretto_companion.feature.game.ui.generated.resources.end_condition_option_custom_with_value
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun GameEndConditionsScope.ScoreOptions(
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

    state.customScoreInput?.let {
        CustomScoreInputDialog(
            state = it,
            dispatch = dispatch,
        )
    }
}

@Composable
internal fun GameEndConditionsScope.TimeOptions(
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

    state.customMinutesInput?.let {
        CustomTimeInputDialog(
            state = it,
            dispatch = dispatch,
        )
    }
}

@Composable
private inline fun <T> GameEndConditionsScope.SpecificOptionsRow(
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
private fun GameEndConditionsScope.CustomValueOption(
    value: String?,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val text = value?.let {
        stringResource(Res.string.end_condition_option_custom_with_value, it)
    } ?: run {
        stringResource(Res.string.end_condition_option_custom)
    }

    OptionPill(
        modifier = modifier,
        text = text,
        selected = selected,
        onClick = onClick,
    )
}
