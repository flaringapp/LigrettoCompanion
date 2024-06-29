package com.flaringapp.ligretto.core.ui.ext

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color

@Composable
fun Modifier.screenStatusBarScrim(
    insets: WindowInsets = WindowInsets.statusBars,
    alpha: Float = 0.25f,
): Modifier = screenStatusBarScrim(
    insets = insets,
    color = MaterialTheme.colorScheme.scrim.copy(alpha = alpha),
)

@Composable
fun Modifier.screenStatusBarScrim(
    insets: WindowInsets = WindowInsets.statusBars,
    color: Color,
): Modifier = drawWithContent {
    drawContent()
    drawRect(
        color = color,
        size = size.copy(height = insets.getTop(this).toFloat()),
    )
}
