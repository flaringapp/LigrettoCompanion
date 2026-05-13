package com.flaringapp.ligretto.common.ui

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.savedstate.serialization.SavedStateConfiguration
import com.flaringapp.ligretto.feature.game.ui.GameNavDestination
import com.flaringapp.ligretto.feature.game.ui.gameGraph
import com.flaringapp.ligretto.feature.game.ui.navigateNewGame
import com.flaringapp.ligretto.feature.game.ui.navigateResumeGame
import com.flaringapp.ligretto.feature.home.ui.HomeNavDestination
import com.flaringapp.ligretto.feature.home.ui.homeGraph
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

internal object AppNavGraph {

    @OptIn(ExperimentalSerializationApi::class)
    val savedStateConfiguration = SavedStateConfiguration {
        serializersModule = SerializersModule {
            polymorphic(NavKey::class) {
                subclassesOfSealed<HomeNavDestination>()
                subclassesOfSealed<GameNavDestination>()
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
