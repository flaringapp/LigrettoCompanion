package com.flaringapp.ligretto.feature.game.ui.common.endconditions.ui.options

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.ui.GameEndConditionsScope

@Composable
fun <T> GameEndConditionsScope.AnimatedContentVisibility(
    componentName: String,
    visible: Boolean,
    state: T,
    modifier: Modifier = Modifier,
    content: @Composable (currentState: T) -> Unit,
) {
    AnimatedContent(
        label = "${componentName}VisibilityAnimation",
        modifier = modifier,
        targetState = state.takeIf { visible },
        contentKey = { it == null },
        transitionSpec = {
            fadeIn() + expandVertically(
                expandFrom = Alignment.Top,
                clip = false,
            ) togetherWith fadeOut() + shrinkVertically(
                shrinkTowards = Alignment.Top,
                clip = false,
            ) using null
        },
    ) { currentState ->
        if (currentState == null) {
            return@AnimatedContent
        }

        content(currentState)
    }
}
