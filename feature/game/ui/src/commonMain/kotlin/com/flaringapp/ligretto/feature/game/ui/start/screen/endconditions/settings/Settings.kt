package com.flaringapp.ligretto.feature.game.ui.start.screen.endconditions.settings

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.GameEndConditionScoreIntent
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.GameEndConditionTimeIntent
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.ui.options.AnimatedContentVisibility
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.ui.options.ScoreOptions
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.ui.options.TimeOptions
import com.flaringapp.ligretto.feature.game.ui.start.GameStartEndConditionsIntent
import com.flaringapp.ligretto.feature.game.ui.start.GameStartState.EndConditions
import com.flaringapp.ligretto.feature.game.ui.start.screen.endconditions.GameStartEndConditionsScope
import ligretto_companion.feature.game.ui.generated.resources.Res
import ligretto_companion.feature.game.ui.generated.resources.start_end_conditions_label
import ligretto_companion.feature.game.ui.generated.resources.start_score_end_condition_expanded_message
import ligretto_companion.feature.game.ui.generated.resources.start_score_end_condition_expanded_title
import ligretto_companion.feature.game.ui.generated.resources.start_score_end_condition_label
import ligretto_companion.feature.game.ui.generated.resources.start_time_end_condition_expanded_message
import ligretto_companion.feature.game.ui.generated.resources.start_time_end_condition_expanded_title
import ligretto_companion.feature.game.ui.generated.resources.start_time_end_condition_label
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun GameStartEndConditionsScope.Settings(
    state: EndConditions,
    dispatch: (GameStartEndConditionsIntent) -> Unit,
    modifier: Modifier = Modifier,
) {
    val conditionsLabel = stringResource(Res.string.start_end_conditions_label)
    val scoreLabel = stringResource(Res.string.start_score_end_condition_label)
    val timeLabel = stringResource(Res.string.start_time_end_condition_label)

    val labelWidth = measureMaxLabelWidth(conditionsLabel, scoreLabel, timeLabel)

    Column(
        modifier = modifier,
    ) {
        LabeledOptionsCompact(
            modifier = Modifier.fillMaxWidth(),
            label = conditionsLabel,
            labelWidth = labelWidth,
        ) {
            ConditionsSelectionCompact(
                scoreSelected = state.score.isEnabled,
                timeSelected = state.time.isEnabled,
                onScoreClick = {
                    val intent = GameEndConditionScoreIntent.SetEnabled(!state.score.isEnabled)
                    dispatch(GameStartEndConditionsIntent.Score(intent))
                },
                onTimeClick = {
                    val intent = GameEndConditionTimeIntent.SetEnabled(!state.time.isEnabled)
                    dispatch(GameStartEndConditionsIntent.Time(intent))
                },
            )
        }

        AnimatedContent(
            label = "SettingsContentAnimation",
            targetState = state,
            contentKey = { it.isExpandedOptionsCompleted },
            transitionSpec = {
                fadeIn() togetherWith fadeOut() using SizeTransform(clip = false)
            },
        ) { currentState ->
            OptionsContent(
                state = currentState,
                dispatch = dispatch,
                scoreLabel = scoreLabel,
                timeLabel = timeLabel,
                labelWidth = labelWidth,
            )
        }
    }
}

@Composable
private fun GameStartEndConditionsScope.OptionsContent(
    state: EndConditions,
    dispatch: (GameStartEndConditionsIntent) -> Unit,
    scoreLabel: String,
    timeLabel: String,
    labelWidth: Dp,
) {
    if (!state.isExpandedOptionsCompleted) {
        ExpandedOptions(
            modifier = Modifier.fillMaxWidth(),
            state = state,
            dispatch = dispatch,
        )
        return
    }

    CompactOptions(
        state = state,
        dispatch = dispatch,
        scoreLabel = scoreLabel,
        timeLabel = timeLabel,
        labelWidth = labelWidth,
    )
}

@Composable
private fun GameStartEndConditionsScope.ExpandedOptions(
    state: EndConditions,
    dispatch: (GameStartEndConditionsIntent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AnimatedContentVisibility(
            componentName = "ScoreOptions",
            visible = state.score.isEnabled,
            state = state.score,
        ) { currentState ->
            LabeledOptionsExpanded(
                modifier = Modifier.padding(top = 40.dp),
                title = stringResource(Res.string.start_score_end_condition_expanded_title),
                message = stringResource(Res.string.start_score_end_condition_expanded_message),
            ) {
                ScoreOptions(
                    state = currentState,
                    dispatch = {
                        dispatch(GameStartEndConditionsIntent.Score(it))
                    },
                )
            }
        }

        AnimatedContentVisibility(
            componentName = "TimeOptions",
            visible = state.time.isEnabled,
            state = state.time,
        ) { currentState ->
            LabeledOptionsExpanded(
                modifier = Modifier.padding(top = 40.dp),
                title = stringResource(Res.string.start_time_end_condition_expanded_title),
                message = stringResource(Res.string.start_time_end_condition_expanded_message),
            ) {
                TimeOptions(
                    state = currentState,
                    dispatch = {
                        dispatch(GameStartEndConditionsIntent.Time(it))
                    },
                )
            }
        }
    }
}

@Composable
private fun GameStartEndConditionsScope.CompactOptions(
    state: EndConditions,
    dispatch: (GameStartEndConditionsIntent) -> Unit,
    scoreLabel: String,
    timeLabel: String,
    labelWidth: Dp,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AnimatedContentVisibility(
            componentName = "ScoreOptions",
            visible = state.score.isEnabled,
            state = state.score,
        ) { currentState ->
            LabeledOptionsCompact(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                label = scoreLabel,
                labelWidth = labelWidth,
            ) {
                ScoreOptions(
                    state = currentState,
                    dispatch = {
                        dispatch(GameStartEndConditionsIntent.Score(it))
                    },
                )
            }
        }

        AnimatedContentVisibility(
            componentName = "TimeOptions",
            visible = state.time.isEnabled,
            state = state.time,
        ) { currentState ->
            LabeledOptionsCompact(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                label = timeLabel,
                labelWidth = labelWidth,
            ) {
                TimeOptions(
                    state = currentState,
                    dispatch = {
                        dispatch(GameStartEndConditionsIntent.Time(it))
                    },
                )
            }
        }
    }
}

@Composable
private fun measureMaxLabelWidth(
    vararg labelsInput: String,
): Dp {
    val density = LocalDensity.current

    val textMeasurer = rememberTextMeasurer(cacheSize = labelsInput.size)
    val textStyle = GameStartEndConditionLabeledOptionsCompactDefaults.LabelTextStyle

    val labels = labelsInput.toList()

    return remember(textMeasurer, density, textStyle, labels) {
        val maxWidth = labels.maxOf {
            textMeasurer.measure(text = it, style = textStyle).size.width
        }
        with(density) { maxWidth.toDp() }
    }
}
