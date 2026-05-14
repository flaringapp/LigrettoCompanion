package com.flaringapp.ligretto.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSerializable
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.serialization.NavBackStackSerializer
import androidx.savedstate.serialization.SavedStateConfiguration
import kotlinx.serialization.PolymorphicSerializer

typealias LigrettoNavBackStack = NavBackStack<LigrettoNavKey>

interface LigrettoNavKey : NavKey

// Cloned from rememberNavBackStack + adjusted generic type, as per documentation
@Composable
fun rememberLigrettoNavBackStack(
    configuration: SavedStateConfiguration,
    vararg elements: LigrettoNavKey,
): LigrettoNavBackStack {
    require(configuration.serializersModule != SavedStateConfiguration.DEFAULT.serializersModule) {
        "You must pass a `SavedStateConfiguration.serializersModule` configured to handle " +
            "`LigrettoNavKey` open polymorphism. Define it with: `polymorphic(LigrettoNavKey::class) { ... }`"
    }
    return rememberSerializable(
        configuration = configuration,
        serializer = NavBackStackSerializer(PolymorphicSerializer(LigrettoNavKey::class)),
    ) {
        NavBackStack(*elements)
    }
}
