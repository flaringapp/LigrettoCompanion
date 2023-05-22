package com.flaringapp.ligretto.core.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive

@Composable
inline fun Ticker(
    interval: Long = 1000L,
    content: @Composable (Boolean) -> Unit,
) {
    var tickTack by remember {
        mutableStateOf(false)
    }

    content(tickTack)

    LaunchedEffect(interval) {
        while (isActive) {
            delay(interval)
            tickTack = !tickTack
        }
    }
}
