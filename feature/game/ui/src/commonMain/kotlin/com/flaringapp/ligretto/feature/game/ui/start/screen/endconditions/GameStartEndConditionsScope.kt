package com.flaringapp.ligretto.feature.game.ui.start.screen.endconditions

import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.flaringapp.ligretto.core.ui.ext.createSharedTransitionModifier
import com.flaringapp.ligretto.feature.game.ui.common.endconditions.ui.GameEndConditionsScope

// A scope for end conditions ui to shorten file/component names
@Immutable
object GameStartEndConditionsScope : GameEndConditionsScope {

    @Composable
    fun Modifier.elementSharedBounds(
        element: GameStartEndConditionsElement,
    ): Modifier {
        val sharedTransitionModifier = createSharedTransitionModifier { animationScope ->
            Modifier.sharedBounds(
                sharedContentState = rememberSharedContentState(element),
                animatedVisibilityScope = animationScope,
                resizeMode = SharedTransitionScope.ResizeMode.scaleToBounds(ContentScale.Fit),
            )
        }
        return then(sharedTransitionModifier)
    }

    @Composable
    fun Modifier.elementSharedContent(
        element: GameStartEndConditionsElement,
    ): Modifier {
        val sharedTransitionModifier = createSharedTransitionModifier { animationScope ->
            Modifier.sharedElement(
                sharedContentState = rememberSharedContentState(element),
                animatedVisibilityScope = animationScope,
            )
        }
        return then(sharedTransitionModifier)
    }
}

enum class GameStartEndConditionsElement {
    DurationTitle,
    ScoreCard,
    TimeCard,
    ScoreTitle,
    TimeTitle,
    ScoreOptions,
    TimeOptions,
}
