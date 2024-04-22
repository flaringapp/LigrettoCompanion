package com.flaringapp.ligretto.core.arch

import androidx.compose.runtime.Composable

// TODO migrate to MP when existing
@Composable
expect fun AndroidBackHandler(
    enabled: Boolean = true,
    onBack: () -> Unit,
)
