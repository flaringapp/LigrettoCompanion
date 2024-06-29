package com.flaringapp.ligretto.core.designsystem

import android.os.Build
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
actual fun platformDynamicColorScheme(isDarkTheme: Boolean): ColorScheme? {
    val supportsDynamicColor = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    if (!supportsDynamicColor) return null

    val context = LocalContext.current

    return if (isDarkTheme) {
        dynamicDarkColorScheme(context)
    } else {
        dynamicLightColorScheme(context)
    }
}
