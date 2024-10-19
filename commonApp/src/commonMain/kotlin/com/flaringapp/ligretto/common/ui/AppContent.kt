package com.flaringapp.ligretto.common.ui

import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
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
        modifier = modifier.background(MaterialTheme.colorScheme.background),
        navController = navController,
        startDestination = HomeDestination.screenId,
        enterTransition = {
            fadeIn(
                animationSpec = spring(stiffness = Spring.StiffnessMedium),
            ) + slideIntoContainer(
                animationSpec = spring(stiffness = Spring.StiffnessMedium),
                towards = SlideDirection.Start,
                initialOffset = { it / 3 },
            )
        },
        exitTransition = {
            fadeOut(
                animationSpec = spring(stiffness = Spring.StiffnessMedium),
            )
        },
        popEnterTransition = {
            fadeIn(
                animationSpec = spring(stiffness = Spring.StiffnessMedium),
            )
        },
        popExitTransition = {
            fadeOut(
                animationSpec = spring(stiffness = Spring.StiffnessMedium),
            ) + slideOutOfContainer(
                animationSpec = spring(stiffness = Spring.StiffnessMedium),
                towards = SlideDirection.End,
                targetOffset = { it / 3 },
            )
        },
        builder = { appNavGraph(navController) },
    )
}
