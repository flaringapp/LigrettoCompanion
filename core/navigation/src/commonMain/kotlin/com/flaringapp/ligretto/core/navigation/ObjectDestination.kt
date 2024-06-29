package com.flaringapp.ligretto.core.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavDeepLink

/**
 * A contract that describes everything about specific navigation destination.
 * It makes easier to define routes and perform navigation.
 */
interface ObjectDestination {

    /**
     * Screen id of described route
     */
    val screenId: String

    /**
     * Full navigation route to a specific screen
     */
    val route: String

    /**
     * List of navigation arguments defined in [route]
     */
    val arguments: List<NamedNavArgument>
        get() = emptyList()

    /**
     * List of navigation deep links to navigate via this route
     */
    val deepLinks: List<NavDeepLink>
        get() = emptyList()
}
