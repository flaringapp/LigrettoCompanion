package com.flaringapp.ligretto.feature.game.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

@Composable
fun GamePlayerImage(
    name: String,
    size: Dp,
    modifier: Modifier = Modifier,
    fallbackText: String = "?",
    shape: Shape = CircleShape,
) {
    val initials = remember(name, fallbackText) {
        if (name.isEmpty()) {
            return@remember fallbackText
        }

        val parts = name.split(' ').filter { it.isNotEmpty() }
        if (parts.isEmpty()) {
            return@remember fallbackText
        }

        if (parts.size == 1) {
            return@remember name.take(2).uppercase()
        }

        "${parts[0].first()}${parts[1].first()}".uppercase()
    }

    val fontSize = with(LocalDensity.current) {
        (size / 2.5f).toSp()
    }

    Box(
        modifier = modifier
            .size(size)
            .clip(shape)
            .background(MaterialTheme.colorScheme.secondary),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = initials,
            color = MaterialTheme.colorScheme.onSecondary,
            fontSize = fontSize,
            style = MaterialTheme.typography.titleSmall,
        )
    }
}
