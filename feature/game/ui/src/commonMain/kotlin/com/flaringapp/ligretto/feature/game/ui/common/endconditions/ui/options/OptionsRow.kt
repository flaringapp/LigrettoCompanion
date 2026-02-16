package com.flaringapp.ligretto.feature.game.ui.common.endconditions.ui.options

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.flaringapp.ligretto.core.designsystem.AppTheme
import com.flaringapp.ligretto.core.ui.ext.fadingEdges
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.ui.GameEndConditionsScope

@Suppress("UnusedReceiverParameter")
@Composable
internal inline fun GameEndConditionsScope.OptionsRow(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(horizontal = 16.dp),
    content: @Composable () -> Unit,
) {
    Row(
        modifier = modifier
            .fadingEdges(contentPadding)
            .horizontalScroll(rememberScrollState())
            .padding(contentPadding),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        content()
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() = with(GameEndConditionsScope) {
    AppTheme {
        OptionsRow {
            OptionPill(text = "50", selected = false, onClick = {})
            OptionPill(text = "100", selected = true, onClick = {})
            OptionPill(text = "150", selected = false, onClick = {})
            OptionPill(text = "Custom", selected = false, onClick = {})
        }
    }
}
