package com.flaringapp.ligretto.feature.game.ui.start.screen.endconditions.settings

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.flaringapp.ligretto.core.ui.ext.UnboundedPaddingLayout
import com.flaringapp.ligretto.feature.game.ui.start.screen.endconditions.GameStartEndConditionsScope
import ligretto_companion.feature.game.ui.generated.resources.Res
import ligretto_companion.feature.game.ui.generated.resources.start_score_end_condition_label
import ligretto_companion.feature.game.ui.generated.resources.start_time_end_condition_label
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun GameStartEndConditionsScope.ConditionsSelectionCompact(
    scoreSelected: Boolean,
    timeSelected: Boolean,
    onScoreClick: () -> Unit,
    onTimeClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    OptionsRow(
        modifier = modifier,
    ) {
        ConditionFlag(
            textRes = Res.string.start_score_end_condition_label,
            selected = scoreSelected,
            onClick = onScoreClick,
        )

        ConditionFlag(
            textRes = Res.string.start_time_end_condition_label,
            selected = timeSelected,
            onClick = onTimeClick,
        )
    }
}

@Composable
private fun GameStartEndConditionsScope.ConditionFlag(
    textRes: StringResource,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    OptionPill(
        modifier = modifier,
        text = stringResource(textRes),
        selected = selected,
        onClick = onClick,
        leadingIcon = {
            AnimatedVisibility(
                label = "SelectedIconVisibilityAnimation",
                visible = selected,
                enter = fadeIn() + expandHorizontally(
                    expandFrom = Alignment.End,
                    clip = false,
                ),
                exit = fadeOut() + shrinkHorizontally(
                    shrinkTowards = Alignment.End,
                    clip = false,
                ),
            ) {
                UnboundedPaddingLayout(
                    padding = PaddingValues(start = 4.dp),
                ) {
                    Icon(
                        modifier = Modifier.padding(end = 8.dp),
                        imageVector = Icons.Rounded.Check,
                        contentDescription = null,
                    )
                }
            }
        },
    )
}
