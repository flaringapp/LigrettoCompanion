@file:OptIn(ExperimentalResourceApi::class)

package com.flaringapp.ligretto.feature.game.ui.start.screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.flaringapp.ligretto.core.designsystem.AppTheme
import ligretto_companion.feature.game.ui.generated.resources.Res
import ligretto_companion.feature.game.ui.generated.resources.start_score_end_condition_label
import ligretto_companion.feature.game.ui.generated.resources.start_score_end_condition_placeholder
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun GameStartEndConditionScore(
    isEnabled: Boolean,
    value: String,
    onEnabledChange: (Boolean) -> Unit,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    GameStartEndConditionItem(
        modifier = modifier,
        label = stringResource(Res.string.start_score_end_condition_label),
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
                text = stringResource(Res.string.start_score_end_condition_placeholder),
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

@Preview
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
