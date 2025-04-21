package com.flaringapp.ligretto.core.ui.ext

import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.LocalViewConfiguration
import kotlin.coroutines.cancellation.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun HandleClickAndHold(
    interactionSource: InteractionSource,
    action: () -> Unit,
    beginHoldThreshold: Long = LocalViewConfiguration.current.longPressTimeoutMillis,
) {
    DetectClickAndHold(
        interactionSource = interactionSource,
        beginHoldThreshold = beginHoldThreshold,
        onClick = action,
        onHold = { initialDelay ->
            ClickAndHoldDefaults.doRepeatedAction(
                initialDelay = initialDelay,
                action = action,
            )
        },
    )
}

@Composable
fun DetectClickAndHold(
    interactionSource: InteractionSource,
    onClick: () -> Unit,
    onHold: suspend (delay: Long) -> Unit,
    beginHoldThreshold: Long = LocalViewConfiguration.current.longPressTimeoutMillis,
) {
    val currentOnClick by rememberUpdatedState(onClick)
    val currentOnHold by rememberUpdatedState(onHold)
    val currentBeginHoldThreshold by rememberUpdatedState(beginHoldThreshold)

    LaunchedEffect(interactionSource) {
        val state = PressHandlingState(coroutineScope = this) { state ->
            val holdDelay = currentBeginHoldThreshold

            try {
                delay(holdDelay)
            } catch (e: CancellationException) {
                if (state.isReleased) {
                    currentOnClick()
                }
                throw e
            }

            currentOnHold(holdDelay)
        }

        interactionSource.interactions.collect { interaction ->
            when (interaction) {
                is PressInteraction.Press -> state.press(interaction)
                is PressInteraction.Release -> state.release(interaction.press)
                is PressInteraction.Cancel -> state.cancel(interaction.press)
            }
        }
    }
}

object ClickAndHoldDefaults {

    suspend fun doRepeatedAction(
        initialDelay: Long,
        action: () -> Unit,
    ) {
        var currentDelay = initialDelay

        while (true) {
            action()

            if (currentDelay > 75) {
                currentDelay -= when {
                    currentDelay > 200 -> 100
                    currentDelay > 100 -> 50
                    else -> 5
                }
            }
            delay(currentDelay)
        }
    }
}

@Stable
private class PressHandlingState(
    private val coroutineScope: CoroutineScope,
    private val pressHandler: suspend (PressHandlingState) -> Unit,
) {

    private val interactions = mutableListOf<Interaction>()

    private var pressHandlingJob: Job? = null

    var isReleased = false
        private set

    fun press(interaction: PressInteraction.Press) {
        interactions += interaction

        if (interactions.size == 1) {
            isReleased = false
            pressHandlingJob = coroutineScope.launch(start = CoroutineStart.UNDISPATCHED) {
                pressHandler(this@PressHandlingState)
            }
        }
    }

    fun release(interaction: PressInteraction.Press) {
        interactions -= interaction
        tryEndPressHandling(release = true)
    }

    fun cancel(interaction: PressInteraction.Press) {
        interactions -= interaction
        tryEndPressHandling(release = false)
    }

    private fun tryEndPressHandling(release: Boolean) {
        if (interactions.isNotEmpty()) {
            return
        }

        isReleased = release
        pressHandlingJob?.cancel()
    }
}
