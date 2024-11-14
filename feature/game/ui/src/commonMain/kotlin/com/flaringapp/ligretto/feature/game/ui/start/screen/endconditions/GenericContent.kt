package com.flaringapp.ligretto.feature.game.ui.start.screen.endconditions

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.flaringapp.ligretto.feature.game.ui.start.GameStartEndConditionsIntent
import com.flaringapp.ligretto.feature.game.ui.start.GameStartScoreEndConditionIntent
import com.flaringapp.ligretto.feature.game.ui.start.GameStartState.EndConditions
import com.flaringapp.ligretto.feature.game.ui.start.GameStartTimeEndConditionIntent
import com.flaringapp.ligretto.feature.game.ui.start.screen.endconditions.selection.ConditionsSelectionExpanded
import com.flaringapp.ligretto.feature.game.ui.start.screen.endconditions.settings.Settings

@Composable
internal fun GameStartEndConditionsScope.GenericContent(
    state: EndConditions,
    dispatch: (GameStartEndConditionsIntent) -> Unit,
    modifier: Modifier = Modifier,
) {
    AnimatedContent(
        modifier = modifier,
        label = "EndConditionsContentAnimation",
        targetState = state,
        contentKey = { it.isExpandedConditionsCompleted },
        transitionSpec = {
            fadeIn() togetherWith fadeOut() using SizeTransform(clip = false)
        },
    ) { currentState ->
        ActualContent(
            state = currentState,
            dispatch = dispatch,
        )
    }
}

@Composable
private fun GameStartEndConditionsScope.ActualContent(
    state: EndConditions,
    dispatch: (GameStartEndConditionsIntent) -> Unit,
) {
    if (!state.isExpandedConditionsCompleted) {
        ConditionsSelectionExpanded(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp, start = 16.dp, end = 16.dp),
            scoreSelected = state.score.isEnabled,
            timeSelected = state.time.isEnabled,
            onScoreSelectionChange = {
                dispatch(GameStartScoreEndConditionIntent.SetEnabled(it))
            },
            onTimeSelectionChange = {
                dispatch(GameStartTimeEndConditionIntent.SetEnabled(it))
            },
        )
        return
    }

    Settings(
        modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp),
        state = state,
        dispatch = dispatch,
    )
}
