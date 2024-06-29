package com.flaringapp.ligretto.core.ui.ext

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun AnimateInitialEnter(
    label: String,
    transition: EnterTransition,
    modifier: Modifier = Modifier,
    animate: Boolean = true,
    content: @Composable AnimatedVisibilityScope.() -> Unit,
) {
    val visibleState = remember(animate) { MutableTransitionState(!animate) }
    if (animate) {
        visibleState.targetState = true
    }

    AnimatedVisibility(
        visibleState = visibleState,
        modifier = modifier,
        label = label,
        enter = transition,
        exit = ExitTransition.None,
        content = content,
    )
}

@OptIn(ExperimentalAnimationApi::class)
fun Modifier.animateInitialEnter(
    // TODO use context receiver when released
    scope: AnimatedVisibilityScope,
    label: String,
    transition: EnterTransition,
    animate: Boolean = true,
): Modifier {
    if (!animate) return this

    return with(scope) {
        animateEnterExit(
            label = label,
            enter = transition,
            exit = ExitTransition.None,
        )
    }
}
