package com.flaringapp.ligretto.android.ui.feature.game.start.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.flaringapp.ligretto.android.ui.AppTheme
import com.flaringapp.ligretto.android.ui.feature.game.start.GameStartEndConditionsIntent
import com.flaringapp.ligretto.android.ui.feature.game.start.GameStartScoreEndConditionIntent
import com.flaringapp.ligretto.android.ui.feature.game.start.GameStartState.EndConditions
import com.flaringapp.ligretto.android.ui.feature.game.start.GameStartTimeEndConditionIntent
import com.flaringapp.ligretto.android.ui.feature.game.start.screen.preview.GameStartEndConditionsProvider

@Composable
fun GameStartEndConditions(
    state: EndConditions,
    dispatch: (GameStartEndConditionsIntent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        GameStartEndConditionScore(
            modifier = Modifier.fillMaxWidth(),
            isEnabled = state.score.isEnabled,
            value = state.score.value,
            onEnabledChange = { isEnabled ->
                dispatch(GameStartScoreEndConditionIntent.SetEnabled(isEnabled))
            },
            onValueChange = { value ->
                dispatch(GameStartScoreEndConditionIntent.ValueChange(value))
            },
        )

        GameStartEndConditionTime(
            modifier = Modifier.fillMaxWidth(),
            isEnabled = state.time.isEnabled,
            hours = state.time.hours,
            minutes = state.time.minutes,
            onEnabledChange = { isEnabled ->
                dispatch(GameStartTimeEndConditionIntent.SetEnabled(isEnabled))
            },
            onHoursChange = { value ->
                dispatch(GameStartTimeEndConditionIntent.HourChange(value))
            },
            onMinutesChange = { value ->
                dispatch(GameStartTimeEndConditionIntent.MinuteChange(value))
            },
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview(
    @PreviewParameter(GameStartEndConditionsProvider::class) state: EndConditions,
) {
    AppTheme {
        GameStartEndConditions(
            state = state,
            dispatch = {},
        )
    }
}