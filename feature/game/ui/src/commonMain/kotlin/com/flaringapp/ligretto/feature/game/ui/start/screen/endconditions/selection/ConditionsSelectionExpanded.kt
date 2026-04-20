package com.flaringapp.ligretto.feature.game.ui.start.screen.endconditions.selection

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.flaringapp.ligretto.core.designsystem.AppTheme
import com.flaringapp.ligretto.feature.game.ui.start.screen.endconditions.GameStartEndConditionsScope
import ligretto_companion.feature.game.ui.generated.resources.Res
import ligretto_companion.feature.game.ui.generated.resources.start_end_conditions_expanded_label
import org.jetbrains.compose.resources.stringResource

@Composable
fun GameStartEndConditionsScope.ConditionsSelectionExpanded(
    scoreSelected: Boolean,
    timeSelected: Boolean,
    onScoreSelectionChange: (Boolean) -> Unit,
    onTimeSelectionChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TitleText()

        ScoreSelectorExpanded(
            modifier = Modifier.padding(top = 24.dp),
            selected = scoreSelected,
            onSelectionChange = onScoreSelectionChange,
        )

        TimeSelectorExpanded(
            modifier = Modifier.padding(top = 12.dp),
            selected = timeSelected,
            onSelectionChange = onTimeSelectionChange,
        )
    }
}

@Composable
private fun TitleText(
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = stringResource(Res.string.start_end_conditions_expanded_label),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.titleLarge,
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewPadded() {
    var isScoreSelected by remember { mutableStateOf(true) }
    var isTimeSelected by remember { mutableStateOf(false) }

    AppTheme {
        GameStartEndConditionsScope.ConditionsSelectionExpanded(
            modifier = Modifier.padding(16.dp),
            scoreSelected = isScoreSelected,
            timeSelected = isTimeSelected,
            onScoreSelectionChange = { isScoreSelected = it },
            onTimeSelectionChange = { isTimeSelected = it },
        )
    }
}
