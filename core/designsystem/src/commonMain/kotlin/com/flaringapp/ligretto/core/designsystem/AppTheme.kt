package com.flaringapp.ligretto.core.designsystem

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable

@Composable
fun AppTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
//    val colors = run {
//        platformDynamicColorScheme(isDarkTheme)
//    } ?: when {
//        isDarkTheme -> appDarkColorScheme()
//        else -> appLightColorScheme()
//    }
    val colors = when {
        isDarkTheme -> appDarkColorScheme()
        else -> appLightColorScheme()
    }
    val typography = createAppTypography()
    val shapes = Shapes()

    MaterialTheme(
        colorScheme = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}
