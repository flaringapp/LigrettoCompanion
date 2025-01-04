package com.flaringapp.ligretto.feature.game.ui.lap.start.screen

import androidx.compose.animation.core.ExperimentalTransitionApi
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.TransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.createChildTransition
import androidx.compose.animation.core.rememberTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.flaringapp.ligretto.feature.game.ui.lap.start.GameLapStartIntent
import com.flaringapp.ligretto.feature.game.ui.lap.start.GameLapStartState
import ligretto_companion.core.ui.generated.resources.img_card_blue
import ligretto_companion.feature.game.ui.generated.resources.Res
import ligretto_companion.feature.game.ui.generated.resources.lap_start_go_text
import ligretto_companion.feature.game.ui.generated.resources.lap_start_round_label
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import kotlinx.coroutines.delay
import ligretto_companion.core.ui.generated.resources.Res as CoreRes

@Composable
internal fun GameLapStartScreenContent(
    state: GameLapStartState,
    dispatch: (GameLapStartIntent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier.fillMaxSize(),
    ) {
        val lapNumber = state.lapNumber ?: return@Surface

        GameLapStartBackgroundNumber(
            modifier = Modifier.fillMaxSize(),
            lapNumber = lapNumber,
        )

        ActualContent(
            lapNumber = lapNumber,
            dispatch = dispatch,
        )
    }
}

@Composable
private fun ActualContent(
    lapNumber: Int,
    dispatch: (GameLapStartIntent) -> Unit,
    modifier: Modifier = Modifier,
) {
    val screenTransitionState = rememberScreenTransitionState(
        dispatch = dispatch,
    )
    val screenTransition = rememberTransition(screenTransitionState)

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(56.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        RoundNumberContent(
            screenTransition = screenTransition,
            lapNumber = lapNumber,
        )

        CardImage()

        GoText(
            modifier = Modifier
                .visibilityAnimationModifierBy(
                    transition = screenTransition,
                    visibleSelector = { it.showGo },
                ),
        )
    }
}

@Composable
private fun RoundNumberContent(
    screenTransition: Transition<ScreenTransitionState>,
    lapNumber: Int,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        RoundLabelText(
            modifier = Modifier
                .visibilityAnimationModifierBy(
                    transition = screenTransition,
                    visibleSelector = { it.showRoundLabel },
                ),
        )

        LapNumberText(
            modifier = Modifier
                .visibilityAnimationModifierBy(
                    transition = screenTransition,
                    visibleSelector = { it.showLapNumber },
                ),
            lapNumber = lapNumber,
        )
    }
}

@Composable
private fun RoundLabelText(
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = stringResource(Res.string.lap_start_round_label),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.titleLarge,
    )
}

@Composable
private fun LapNumberText(
    lapNumber: Int,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = lapNumber.toString(),
        fontSize = 100.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.displayLarge,
    )
}

@Composable
private fun CardImage(
    modifier: Modifier = Modifier,
) {
    val animateTransitionState = remember { MutableTransitionState(false) }
    animateTransitionState.targetState = true

    val animateTransition = rememberTransition(animateTransitionState)

    val rotation = animateTransition.animateFloat(
        targetValueByState = { if (it) 360f * 4 else 0f },
        transitionSpec = {
            tween(
                durationMillis = 3_000,
                easing = LinearOutSlowInEasing,
            )
        },
    )

    Image(
        modifier = modifier
            .height(200.dp)
            .graphicsLayer {
                rotationZ = rotation.value
            },
        painter = painterResource(CoreRes.drawable.img_card_blue),
        contentDescription = null,
    )
}

@Composable
private fun GoText(
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = stringResource(Res.string.lap_start_go_text),
        fontSize = 80.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.displayLarge,
    )
}

@Composable
private fun rememberScreenTransitionState(
    dispatch: (GameLapStartIntent) -> Unit,
): TransitionState<ScreenTransitionState> {
    val transitionState = remember { MutableTransitionState(ScreenTransitionState()) }

    LaunchedEffect(Unit) {
        delay(500)
        transitionState.targetState = transitionState.targetState.copy(showRoundLabel = true)

        delay(500)
        transitionState.targetState = transitionState.targetState.copy(showLapNumber = true)

        delay(1500)
        transitionState.targetState = transitionState.targetState.copy(showGo = true)

        delay(1000)

        dispatch(GameLapStartIntent.StartLap)
    }

    return transitionState
}

@OptIn(ExperimentalTransitionApi::class)
@Composable
private inline fun Modifier.visibilityAnimationModifierBy(
    transition: Transition<ScreenTransitionState>,
    visibleSelector: (ScreenTransitionState) -> Boolean,
): Modifier {
    val visibleTransition = transition.createChildTransition { visibleSelector(it) }

    val alpha = visibleTransition.animateFloat { visible ->
        if (visible) 1f else 0f
    }

    val scale = visibleTransition.animateFloat(
        transitionSpec = {
            spring(
                dampingRatio = 0.4f,
                stiffness = 800f,
            )
        },
    ) { visible ->
        if (visible) 1f else 0.5f
    }

    return graphicsLayer {
        this.alpha = alpha.value
        scaleX = scale.value
        scaleY = scale.value
    }
}

private data class ScreenTransitionState(
    val showRoundLabel: Boolean = false,
    val showLapNumber: Boolean = false,
    val showGo: Boolean = false,
)
