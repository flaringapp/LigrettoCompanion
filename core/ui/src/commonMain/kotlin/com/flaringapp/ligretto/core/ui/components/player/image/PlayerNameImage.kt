package com.flaringapp.ligretto.core.ui.components.player.image

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.flaringapp.ligretto.core.designsystem.AppTheme

@Composable
fun PlayerNameImage(
    name: String,
    modifier: Modifier = Modifier,
    fallbackText: String = "?",
    shape: Shape = PlayerImageDefaults.Shape,
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

    BoxWithConstraints(
        modifier = modifier
            // Order matters: defaultMinSize floors the min so aspectRatio resolves a finite square.
            // This allows aspectRatio to produce non-zero and non-infinite size.
            .defaultMinSize(
                minWidth = PlayerImageDefaults.DefaultSize,
                minHeight = PlayerImageDefaults.DefaultSize,
            )
            .aspectRatio(1f)
            .clip(shape)
            .background(MaterialTheme.colorScheme.secondary),
        contentAlignment = Alignment.Center,
    ) {
        val fontSize = with(LocalDensity.current) {
            (constraints.maxHeight / 2.5f).toSp()
        }

        Text(
            text = initials,
            color = MaterialTheme.colorScheme.onSecondary,
            fontSize = fontSize,
            style = MaterialTheme.typography.titleSmall,
        )
    }
}

@Preview
@Composable
private fun PreviewOneLetter() {
    BasePreview(name = "A")
}

@Preview
@Composable
private fun PreviewOneWord() {
    BasePreview(name = "Andreo")
}

@Preview
@Composable
private fun PreviewTwoWords() {
    BasePreview(name = "Andreo Ligrettio")
}

@Composable
private fun BasePreview(
    name: String,
    modifier: Modifier = Modifier,
) {
    AppTheme {
        PlayerNameImage(
            modifier = modifier.size(40.dp),
            name = name,
        )
    }
}
