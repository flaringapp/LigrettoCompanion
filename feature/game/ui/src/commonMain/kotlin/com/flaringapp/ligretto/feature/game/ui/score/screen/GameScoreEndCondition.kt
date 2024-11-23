package com.flaringapp.ligretto.feature.game.ui.score.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Scoreboard
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ligretto_companion.feature.game.ui.generated.resources.Res
import ligretto_companion.feature.game.ui.generated.resources.scores_end_condition_score_label
import ligretto_companion.feature.game.ui.generated.resources.scores_end_condition_score_value
import ligretto_companion.feature.game.ui.generated.resources.scores_end_condition_time_label
import ligretto_companion.feature.game.ui.generated.resources.scores_end_condition_time_value
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.pluralStringResource
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun GameScoreEndConditionScore(
    score: Int,
    modifier: Modifier = Modifier,
) {
    val value = pluralStringResource(
        Res.plurals.scores_end_condition_score_value,
        score,
        score,
    )

    EndCondition(
        modifier = modifier,
        icon = Icons.Outlined.Scoreboard,
        name = Res.string.scores_end_condition_score_label,
        value = value,
    )
}

@Composable
internal fun GameScoreEndConditionTime(
    minutes: Int,
    modifier: Modifier = Modifier,
) {
    val value = pluralStringResource(
        Res.plurals.scores_end_condition_time_value,
        minutes,
        minutes,
    )

    EndCondition(
        modifier = modifier,
        icon = Icons.Outlined.Timer,
        name = Res.string.scores_end_condition_time_label,
        value = value,
    )
}

@Composable
private fun EndCondition(
    icon: ImageVector,
    name: StringResource,
    value: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        EndConditionIcon(
            icon = icon,
        )

        Column(
            modifier = Modifier.padding(start = 12.dp),
        ) {
            EndConditionNameText(
                text = stringResource(name),
            )

            EndConditionValueText(
                text = value,
            )
        }
    }
}

// TODO reuse from game start card
@Composable
private fun EndConditionIcon(
    icon: ImageVector,
    modifier: Modifier = Modifier,
) {
    Icon(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.surfaceContainer,
                shape = CircleShape,
            )
            .padding(12.dp),
        imageVector = icon,
        contentDescription = null,
        tint = MaterialTheme.colorScheme.onSurfaceVariant,
    )
}

@Composable
private fun EndConditionNameText(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.bodySmall,
    )
}

@Composable
private fun EndConditionValueText(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = text,
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.bodyLarge,
    )
}
