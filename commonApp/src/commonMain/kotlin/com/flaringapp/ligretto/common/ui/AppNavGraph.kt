package com.flaringapp.ligretto.common.ui

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.savedstate.serialization.SavedStateConfiguration
import com.flaringapp.ligretto.feature.game.ui.gameGraph
import com.flaringapp.ligretto.feature.game.ui.gameNavDestinations
import com.flaringapp.ligretto.feature.game.ui.navigateNewGame
import com.flaringapp.ligretto.feature.game.ui.navigateResumeGame
import com.flaringapp.ligretto.feature.home.ui.homeGraph
import com.flaringapp.ligretto.feature.home.ui.homeNavDestinations
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

internal object AppNavGraph {

    val savedStateConfiguration = SavedStateConfiguration {
        serializersModule = SerializersModule {
            polymorphic(NavKey::class) {
                homeNavDestinations()
                gameNavDestinations()
            }
        }
    }
}

internal fun EntryProviderScope<NavKey>.appNavGraph(backStack: NavBackStack<NavKey>) {
    homeGraph(
        backStack = backStack,
        startGame = backStack::navigateNewGame,
        resumeGame = backStack::navigateResumeGame,
    )
    gameGraph(
        backStack = backStack,
    )
}
