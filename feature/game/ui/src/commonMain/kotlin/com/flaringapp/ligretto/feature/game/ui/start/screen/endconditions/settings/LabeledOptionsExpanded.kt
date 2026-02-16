package com.flaringapp.ligretto.feature.game.ui.start.screen.endconditions.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.flaringapp.ligretto.core.designsystem.AppTheme
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.preview.GameEndConditionsScoreLimitStateProvider
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.ui.options.ScoreOptions
import com.flaringapp.ligretto.feature.game.ui.start.screen.endconditions.GameStartEndConditionsScope

@Suppress("UnusedReceiverParameter")
@Composable
internal inline fun GameStartEndConditionsScope.LabeledOptionsExpanded(
    title: String,
    message: String,
    modifier: Modifier = Modifier,
    optionsContent: @Composable () -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TitleText(
            text = title,
        )

        MessageText(
            modifier = Modifier.padding(top = 4.dp),
            text = message,
        )

        Box(
            modifier = Modifier.padding(top = 24.dp),
        ) {
            optionsContent()
        }
    }
}

@Composable
private fun TitleText(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = text,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.titleLarge,
    )
}

@Composable
private fun MessageText(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = text,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.bodyMedium,
    )
}

@Preview(showBackground = true)
@Composable
private fun Preview() = with(GameStartEndConditionsScope) {
    AppTheme {
        LabeledOptionsExpanded(
            title = "Set Score",
            message = "Select the target score to win the game",
        ) {
            ScoreOptions(
                state = GameEndConditionsScoreLimitStateProvider.enabled(),
                dispatch = {},
            )
        }
    }
}
