package com.flaringapp.ligretto.core.ui.ext

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

val WindowInsets.Companion.screen
    @Composable
    get() = WindowInsets.systemBars.union(WindowInsets.displayCutout)

@Composable
fun Modifier.screenWindowInsetsPadding(): Modifier = this
    .windowInsetsPadding(WindowInsets.screen)
