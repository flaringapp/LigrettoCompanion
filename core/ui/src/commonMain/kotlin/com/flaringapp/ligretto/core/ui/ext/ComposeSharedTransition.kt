@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.flaringapp.ligretto.core.ui.ext

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier

@Suppress("ComposeCompositionLocalUsage")
val LocalSharedTransitionScope = compositionLocalOf<SharedTransitionScope?> { null }

@Suppress("ComposeCompositionLocalUsage")
val LocalSharedTransitionAnimationScope = compositionLocalOf<AnimatedVisibilityScope?> { null }

@Suppress("ComposeUnstableReceiver")
@Composable
fun SharedTransitionScope.ProvideSharedTransitionAnimationScopes(
    animationScope: AnimatedVisibilityScope,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalSharedTransitionScope provides this,
        LocalSharedTransitionAnimationScope provides animationScope,
        content = content,
    )
}

@Suppress("ModifierFactoryExtensionFunction")
@Composable
fun createSharedTransitionModifier(
    block: @Composable SharedTransitionScope.(AnimatedVisibilityScope) -> Modifier,
): Modifier {
    val sharedTransitionScope = LocalSharedTransitionScope.current ?: return Modifier
    val animationScope = LocalSharedTransitionAnimationScope.current ?: return Modifier

    return with(sharedTransitionScope) {
        block(animationScope)
    }
}

@Composable
fun Modifier.animateSharedTransitionVisibility(
    enter: EnterTransition = fadeIn(),
    exit: ExitTransition = fadeOut(),
    label: String = "animateEnterExit",
): Modifier {
    val animationScope = LocalSharedTransitionAnimationScope.current ?: return this

    return with(animationScope) {
        animateEnterExit(
            enter = enter,
            exit = exit,
            label = label,
        )
    }
}
