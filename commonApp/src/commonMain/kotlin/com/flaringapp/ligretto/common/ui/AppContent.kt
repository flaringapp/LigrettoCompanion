package com.flaringapp.ligretto.common.ui

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
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
                animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
            ) + slideInHorizontally(
                animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
                initialOffsetX = { it / 3 },
            )
        },
        exitTransition = {
            fadeOut(
                animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
            )
        },
        popEnterTransition = {
            fadeIn(
                animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
            )
        },
        popExitTransition = {
            fadeOut(
                animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
            ) + slideOutHorizontally(
                animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
                targetOffsetX = { it / 3 },
            )
        },
        builder = { appNavGraph(navController) },
    )
}
