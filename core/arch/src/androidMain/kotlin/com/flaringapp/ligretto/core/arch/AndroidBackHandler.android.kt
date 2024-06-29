package com.flaringapp.ligretto.core.arch

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable

@Composable
actual fun AndroidBackHandler(
    enabled: Boolean,
    onBack: () -> Unit,
) {
    BackHandler(
        enabled = enabled,
        onBack = onBack,
    )
}
