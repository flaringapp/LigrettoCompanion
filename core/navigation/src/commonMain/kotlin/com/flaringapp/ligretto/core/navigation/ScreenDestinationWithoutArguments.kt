package com.flaringapp.ligretto.core.navigation

import androidx.navigation.NamedNavArgument

abstract class ScreenDestinationWithoutArguments : ScreenDestination {

    final override val route: String
        get() = screenId

    final override val arguments: List<NamedNavArgument> = emptyList()

    fun route(): String = route
}
