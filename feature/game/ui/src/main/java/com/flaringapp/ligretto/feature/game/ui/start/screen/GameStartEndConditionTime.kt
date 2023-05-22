package com.flaringapp.ligretto.feature.game.ui.start.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.flaringapp.ligretto.core.designsystem.AppTheme
import com.flaringapp.ligretto.feature.game.ui.R

@Composable
internal fun GameStartEndConditionTime(
    isEnabled: Boolean,
    hours: String,
    minutes: String,
    onEnabledChange: (Boolean) -> Unit,
    onHoursChange: (String) -> Unit,
    onMinutesChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    GameStartEndConditionItem(
        modifier = modifier,
        label = stringResource(R.string.start_time_end_condition_label),
        isEnabled = isEnabled,
        onEnabledChange = onEnabledChange,
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            ValueInput(
                modifier = Modifier.width(80.dp),
                value = hours,
                placeholder = stringResource(R.string.start_time_end_condition_hours_placeholder),
                imeAction = ImeAction.Next,
                onValueChange = onHoursChange,
            )

            Text(
                text = stringResource(R.string.start_time_end_condition_split)
            )

            ValueInput(
                modifier = Modifier.width(80.dp),
                value = minutes,
                placeholder = stringResource(R.string.start_time_end_condition_minutes_placeholder),
                imeAction = ImeAction.Done,
                onValueChange = onMinutesChange,
            )
        }
    }
}

@Composable
private fun ValueInput(
    value: String,
    placeholder: String,
    imeAction: ImeAction,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
        placeholder = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = placeholder,
                textAlign = TextAlign.Center,
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = imeAction,
        ),
        singleLine = true,
    )
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    AppTheme {
        GameStartEndConditionTime(
            modifier = Modifier.fillMaxWidth(),
            isEnabled = true,
            hours = "1",
            minutes = "15",
            onEnabledChange = {},
            onHoursChange = {},
            onMinutesChange = {},
        )
    }
}
