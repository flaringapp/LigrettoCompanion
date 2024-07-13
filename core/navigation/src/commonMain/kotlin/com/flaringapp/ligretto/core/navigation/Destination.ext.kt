package com.flaringapp.ligretto.core.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.PopUpToBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.navigation

fun NavGraphBuilder.composableDestination(
    destination: ScreenDestination,
    content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit,
) = composable(
    route = destination.route,
    arguments = destination.arguments,
    deepLinks = destination.deepLinks,
) { entry ->
    content(entry)
}

fun NavGraphBuilder.navigationDestination(
    startDestination: ScreenDestination,
    destination: ScreenDestination,
    builder: NavGraphBuilder.() -> Unit,
) = navigation(
    startDestination = startDestination.screenId,
    route = destination.route,
    arguments = destination.arguments,
    deepLinks = destination.deepLinks,
    builder = builder,
)

fun NavGraphBuilder.dialogDestination(
    destination: DialogDestination,
    content: @Composable (NavBackStackEntry) -> Unit,
) = dialog(
    route = destination.route,
    arguments = destination.arguments,
    deepLinks = destination.deepLinks,
    dialogProperties = destination.dialogProperties,
) { entry ->
    content(entry)
}

fun NavController.navigateDestination(
    destination: ScreenDestinationWithoutArguments,
    builder: NavOptionsBuilder.() -> Unit,
) {
    navigate(
        destination.route(),
        builder,
    )
}

fun NavOptionsBuilder.popUpToDestination(
    destination: ScreenDestination,
    popUpToBuilder: PopUpToBuilder.() -> Unit = {},
) {
    popUpTo(
        route = destination.screenId,
        popUpToBuilder = popUpToBuilder,
    )
}
