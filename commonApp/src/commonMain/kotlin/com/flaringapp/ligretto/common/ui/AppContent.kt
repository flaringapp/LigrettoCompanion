package com.flaringapp.ligretto.common.ui

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseInQuart
import androidx.compose.animation.core.EaseOutQuart
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.flaringapp.ligretto.core.designsystem.AppTheme
import com.flaringapp.ligretto.feature.home.ui.HomeDestination

@Composable
fun AppContent() {
    AppTheme {
        RootScreen()
    }
}

@Composable
private fun RootScreen(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()

    NavHost(
        modifier = modifier.background(MaterialTheme.colorScheme.surface),
        navController = navController,
        startDestination = HomeDestination,
        enterTransition = {
            fadeIn(
                animationSpec = enterAnimationSpec(),
            ) + slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Start,
                animationSpec = enterAnimationSpec(),
                initialOffset = { it.slideFraction },
            )
        },
        exitTransition = {
            fadeOut(
                animationSpec = exitAnimationSpec(),
            ) + slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Start,
                animationSpec = exitAnimationSpec(),
                targetOffset = { it.slideFraction },
            )
        },
        popEnterTransition = {
            fadeIn(
                animationSpec = enterAnimationSpec(),
            ) + slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.End,
                animationSpec = enterAnimationSpec(),
                initialOffset = { it.slideFraction },
            )
        },
        popExitTransition = {
            fadeOut(
                animationSpec = exitAnimationSpec(),
            ) + slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.End,
                animationSpec = exitAnimationSpec(),
                targetOffset = { it.slideFraction },
            )
        },
        builder = { appNavGraph(navController) },
    )
}

private fun <T> enterAnimationSpec() = tween<T>(
    durationMillis = 450,
    delayMillis = 180,
    easing = EaseOutQuart,
)

private fun <T> exitAnimationSpec() = tween<T>(
    durationMillis = 200,
    easing = EaseInQuart,
)

private val Int.slideFraction: Int
    get() = this / 10
