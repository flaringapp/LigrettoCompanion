package com.flaringapp.ligretto.feature.game.ui.start.screen.endconditions.settings

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.isUnspecified
import com.flaringapp.ligretto.feature.game.ui.start.screen.endconditions.GameStartEndConditionsScope

internal object GameStartEndConditionLabeledOptionsCompactDefaults {

    val LabelTextStyle: TextStyle
        @Composable
        get() = MaterialTheme.typography.titleMedium
}

@Composable
internal fun GameStartEndConditionsScope.LabeledOptionsCompact(
    label: String,
    modifier: Modifier = Modifier,
    labelWidth: Dp = Dp.Unspecified,
    optionsContent: @Composable () -> Unit,
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.surfaceContainer,
        shape = RoundedCornerShape(12.dp),
    ) {
        Row(
            modifier = Modifier.height(IntrinsicSize.Max),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            val labelMinWidthModifier = labelWidth.let {
                if (it.isUnspecified) {
                    return@let Modifier
                }
                Modifier.widthIn(min = it)
            }

            Text(
                modifier = Modifier
                    .padding(start = 16.dp, end = 8.dp)
                    .then(labelMinWidthModifier),
                text = label,
                style = GameStartEndConditionLabeledOptionsCompactDefaults.LabelTextStyle,
            )

            VerticalDivider(
                color = MaterialTheme.colorScheme.surfaceContainerHighest,
            )

            optionsContent()
        }
    }
}
