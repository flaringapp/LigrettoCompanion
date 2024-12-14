package com.flaringapp.ligretto.feature.game.ui.settings.screen.endconditions

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp

@Composable
internal fun GameSettingsEndConditionsScope.ConditionSettings(
    conditionName: String,
    selected: Boolean,
    onSelectionChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    optionsContent: @Composable () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        CheckboxContent(
            conditionName = conditionName,
            selected = selected,
            onSelectionChanged = onSelectionChanged,
        )

        AnimatedVisibility(
            label = "OptionsContentVisibilityAnimation",
            visible = selected,
            enter = fadeIn(
                animationSpec = spring(stiffness = Spring.StiffnessMedium),
            ) + expandVertically(
                animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
                expandFrom = Alignment.Top,
                clip = false,
            ),
            exit = fadeOut(
                animationSpec = spring(stiffness = Spring.StiffnessMedium),
            ) + shrinkVertically(
                animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
                shrinkTowards = Alignment.Top,
                clip = false,
            ),
        ) {
            Box(
                modifier = Modifier.padding(top = 4.dp, bottom = 12.dp),
            ) {
                optionsContent()
            }
        }
    }
}

@Composable
private fun CheckboxContent(
    conditionName: String,
    selected: Boolean,
    onSelectionChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    val interactionSource = remember { MutableInteractionSource() }

    Row(
        modifier = modifier
            .toggleable(
                value = selected,
                onValueChange = onSelectionChanged,
                interactionSource = interactionSource,
                indication = null,
                role = Role.Checkbox,
            )
            .padding(12.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Checkbox(
            modifier = Modifier
                .indication(
                    interactionSource = interactionSource,
                    indication = ripple(bounded = false),
                )
                .padding(2.dp),
            checked = selected,
            onCheckedChange = null,
        )

        ConditionNameText(
            name = conditionName,
        )
    }
}

@Composable
private fun ConditionNameText(
    name: String,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = name,
        style = MaterialTheme.typography.titleSmall,
    )
}
