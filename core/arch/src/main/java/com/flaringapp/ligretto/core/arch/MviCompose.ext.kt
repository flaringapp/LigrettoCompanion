package com.flaringapp.ligretto.core.arch

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import kotlinx.coroutines.flow.Flow

@Composable
fun <Effect : UiEffect> ConsumeEffects(
    flow: Flow<Effect>,
    consumeEffect: (effect: Effect) -> Unit,
) {
    val consumeEffectState by rememberUpdatedState(consumeEffect)

    LaunchedEffect(Unit) {
        flow.collect {
            consumeEffectState(it)
        }
    }
}
