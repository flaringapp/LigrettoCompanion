package com.flaringapp.ligretto.core.designsystem

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

@Composable
fun AppTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
//    val dynamicColor = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    val colors = when {
//        dynamicColor && isDarkTheme -> dynamicDarkColorScheme(LocalContext.current)
//        dynamicColor && !isDarkTheme -> dynamicLightColorScheme(LocalContext.current)
        isDarkTheme -> darkColorScheme()
        else -> lightColorScheme()
    }
    val typography = Typography()
    val shapes = Shapes()

    MaterialTheme(
        colorScheme = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}
