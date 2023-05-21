package com.flaringapp.ligretto.android.ui.feature.game.start.screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.flaringapp.ligretto.android.R
import com.flaringapp.ligretto.core.designsystem.AppTheme

@Composable
fun GameStartEndConditionScore(
    isEnabled: Boolean,
    value: String,
    onEnabledChange: (Boolean) -> Unit,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    GameStartEndConditionItem(
        modifier = modifier,
        label = stringResource(R.string.start_score_end_condition_label),
        isEnabled = isEnabled,
        onEnabledChange = onEnabledChange,
    ) {
        ScoreInput(
            modifier = Modifier.width(80.dp),
            value = value,
            onValueChange = onValueChange,
        )
    }
}

@Composable
private fun ScoreInput(
    value: String,
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
                text = stringResource(R.string.start_score_end_condition_placeholder),
                textAlign = TextAlign.Center,
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done,
        ),
        singleLine = true,
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewEnabled() {
    AppTheme {
        GameStartEndConditionScore(
            modifier = Modifier.fillMaxWidth(),
            isEnabled = true,
            value = "100",
            onEnabledChange = {},
            onValueChange = {},
        )
    }
}
