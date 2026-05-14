package com.flaringapp.ligretto.common.ui

import androidx.compose.animation.core.EaseInQuart
import androidx.compose.animation.core.EaseOutQuart
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.scene.DialogSceneStrategy
import androidx.navigation3.ui.NavDisplay
import com.flaringapp.ligretto.core.designsystem.AppTheme
import com.flaringapp.ligretto.core.navigation.LigrettoNavKey
import com.flaringapp.ligretto.core.navigation.rememberLigrettoNavBackStack
import com.flaringapp.ligretto.feature.home.ui.HomeScreenDestination

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
    val backStack = rememberLigrettoNavBackStack(
        configuration = AppNavGraph.savedStateConfiguration,
        HomeScreenDestination,
    )
    val dialogSceneStrategy = remember { DialogSceneStrategy<LigrettoNavKey>() }

    NavDisplay(
        modifier = modifier.background(MaterialTheme.colorScheme.surface),
        backStack = backStack,
        onBack = backStack::removeLastOrNull,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator(),
        ),
        sceneStrategies = listOf(dialogSceneStrategy),
        entryProvider = entryProvider {
            appNavGraph(backStack)
        },
        transitionSpec = {
            fadeIn(
                animationSpec = enterAnimationSpec(),
            ) + slideInHorizontally(
                animationSpec = enterAnimationSpec(),
                initialOffsetX = { it.slideFraction },
            ) togetherWith fadeOut(
                animationSpec = exitAnimationSpec(),
            ) + slideOutHorizontally(
                animationSpec = exitAnimationSpec(),
                targetOffsetX = { -it.slideFraction },
            )
        },
        popTransitionSpec = {
            fadeIn(
                animationSpec = enterAnimationSpec(),
            ) + slideInHorizontally(
                animationSpec = enterAnimationSpec(),
                initialOffsetX = { -it.slideFraction },
            ) togetherWith fadeOut(
                animationSpec = exitAnimationSpec(),
            ) + slideOutHorizontally(
                animationSpec = exitAnimationSpec(),
                targetOffsetX = { it.slideFraction },
            )
        },
        predictivePopTransitionSpec = {
            fadeIn(
                animationSpec = enterAnimationSpec(),
            ) + slideInHorizontally(
                animationSpec = enterAnimationSpec(),
                initialOffsetX = { -it.slideFraction },
            ) togetherWith fadeOut(
                animationSpec = exitAnimationSpec(),
            ) + slideOutHorizontally(
                animationSpec = exitAnimationSpec(),
                targetOffsetX = { it.slideFraction },
            )
        },
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
