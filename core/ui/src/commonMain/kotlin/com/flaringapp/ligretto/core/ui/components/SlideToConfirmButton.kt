package com.flaringapp.ligretto.core.ui.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowRight
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.FastForward
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.flaringapp.ligretto.core.designsystem.AppTheme
import com.revenuecat.purchases.slidetounlock.HintTexts
import com.revenuecat.purchases.slidetounlock.SlideState
import com.revenuecat.purchases.slidetounlock.SlideToUnlock
import com.revenuecat.purchases.slidetounlock.SlideToUnlockColors

@Composable
fun SlideToConfirmButton(
    hint: String,
    actionLabel: String,
    onConfirmed: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var slideState by remember { mutableStateOf(SlideState.Idle) }

    val hintTexts = remember(hint) {
        HintTexts(
            defaultText = hint,
            slidedText = "",
        )
    }

    SlideToUnlock(
        modifier = modifier,
        state = slideState,
        onSlideCompleted = {
            slideState = SlideState.Success
            onConfirmed()
        },
        colors = SlideToConfirmColors,
        hintTexts = hintTexts,
        hintPaddings = PaddingValues.Zero,
        actionLabel = actionLabel,
        thumb = { state, slideFraction, colors, size, _ ->
            Thumb(
                state = state,
                slideFraction = slideFraction,
                thumbSize = size,
                colors = colors,
            )
        },
    )
}

@Composable
private fun Thumb(
    state: SlideState,
    slideFraction: Float,
    thumbSize: DpSize,
    colors: SlideToUnlockColors,
    modifier: Modifier = Modifier,
) {
    val backgroundModifier = colors.thumbBrush(slideFraction)?.let { brush ->
        Modifier.background(brush = brush, shape = CircleShape)
    } ?: run {
        Modifier.background(color = colors.thumbColor(), shape = CircleShape)
    }

    Box(
        modifier = modifier
            .size(thumbSize)
            .then(backgroundModifier),
        contentAlignment = Alignment.Center,
    ) {
        AnimatedThumbContent(
            state = state,
            colors = colors,
        )
    }
}

@Composable
private fun AnimatedThumbContent(
    state: SlideState,
    colors: SlideToUnlockColors,
    modifier: Modifier = Modifier,
) {
    AnimatedContent(
        modifier = modifier,
        targetState = state,
        transitionSpec = {
            fadeIn(
                animationSpec = spring(stiffness = Spring.StiffnessMedium),
            ) togetherWith fadeOut(
                animationSpec = spring(stiffness = Spring.StiffnessMedium),
            ) using null
        },
        contentAlignment = Alignment.Center,
        label = "ThumbContentAnimation",
    ) { currentState ->
        when (currentState) {
            SlideState.Loading -> {
                CircularProgressIndicator(
                    color = colors.progressColor(),
                    strokeWidth = 2.dp,
                )
            }

            SlideState.Success -> {
                Icon(
                    imageVector = Icons.Rounded.FastForward,
                    tint = colors.successIconColor(),
                    contentDescription = null,
                )
            }

            SlideState.Error -> {
                Icon(
                    imageVector = Icons.Rounded.Close,
                    tint = colors.errorIconColor(),
                    contentDescription = null,
                )
            }

            SlideState.Idle -> {
                Icon(
                    modifier = Modifier.size(40.dp),
                    imageVector = Icons.AutoMirrored.Rounded.ArrowRight,
                    tint = colors.thumbIconColor(),
                    contentDescription = null,
                )
            }
        }
    }
}

private object SlideToConfirmColors : SlideToUnlockColors {

    @Composable
    override fun trackColor(slideFraction: Float): Color = lerp(
        fraction = 1f - slideFraction,
        start = MaterialTheme.colorScheme.primaryFixedDim,
        stop = MaterialTheme.colorScheme.primaryContainer,
    )

    @Composable
    override fun trackBrush(slideFraction: Float): Brush? = null

    @Composable
    override fun hintColor(slideFraction: Float): Color =
        MaterialTheme.colorScheme.onSurfaceVariant.copy(
            alpha = (1f - slideFraction * 2f).coerceAtLeast(0f),
        )

    @Composable
    override fun slidedHintColor(): Color = MaterialTheme.colorScheme.onSurfaceVariant

    @Composable
    override fun thumbColor(): Color = MaterialTheme.colorScheme.primary

    @Composable
    override fun thumbBrush(slideFraction: Float): Brush? = null

    @Composable
    override fun thumbIconColor(): Color = MaterialTheme.colorScheme.onPrimary

    @Composable
    override fun progressColor(): Color = MaterialTheme.colorScheme.onPrimary

    @Composable
    override fun successIconColor(): Color = MaterialTheme.colorScheme.onPrimary
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    AppTheme {
        SlideToConfirmButton(
            modifier = Modifier.padding(16.dp),
            hint = "Slide to confirm",
            actionLabel = "ActionLabel",
            onConfirmed = {},
        )
    }
}
