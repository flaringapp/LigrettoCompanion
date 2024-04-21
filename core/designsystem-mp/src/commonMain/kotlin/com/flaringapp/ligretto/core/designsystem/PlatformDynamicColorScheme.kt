package com.flaringapp.ligretto.core.designsystem

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable

@Composable
expect fun platformDynamicColorScheme(isDarkTheme: Boolean): ColorScheme?
