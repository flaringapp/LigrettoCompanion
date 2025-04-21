package com.flaringapp.ligretto.core.ui.ext

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter
import org.jetbrains.compose.ui.tooling.preview.PreviewParameterProvider

/**
 * Creates fading edge effect of the content. Affects downstream modifiers and child elements.
 * Example usage:
 * ```
 * Box(
 *     modifier = Modifier
 *         .size(100.dp)
 *         .fadingEdges(PaddingValues(vertical = 20.dp)
 *         .background(Color.Blue)
 * ) {
 *     Box(
 *         modifier = Modifier
 *             .fillMaxSize()
 *             .padding(10.dp)
 *             .background(Color.Yellow)
 *     )
 * }
 * ```
 * @param offset Size of the fade effect.
 */
fun Modifier.fadingEdges(
    offset: PaddingValues,
) = this
    .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
    .drawWithContent {
        drawFadingEdges(offset)
    }

private fun ContentDrawScope.drawFadingEdges(offset: PaddingValues) {
    drawContent()

    val startOffset = offset.calculateStartPadding(layoutDirection)
    val topOffset = offset.calculateTopPadding()
    val endOffset = offset.calculateEndPadding(layoutDirection)
    val bottomOffset = offset.calculateBottomPadding()

    startOffset.takeIf { it > 0.dp }?.toPx()?.let { fadeWidth ->
        drawRect(
            brush = Brush.horizontalGradient(
                0f to Color.Transparent,
                1f to Color.White,
                endX = fadeWidth,
            ),
            size = Size(width = fadeWidth, height = size.height),
            blendMode = BlendMode.DstIn,
        )
    }
    topOffset.takeIf { it > 0.dp }?.toPx()?.let { fadeHeight ->
        drawRect(
            brush = Brush.verticalGradient(
                0f to Color.Transparent,
                1f to Color.White,
                endY = fadeHeight,
            ),
            size = Size(width = size.width, height = fadeHeight),
            blendMode = BlendMode.DstIn,
        )
    }
    endOffset.takeIf { it > 0.dp }?.toPx()?.let { fadeWidth ->
        val startX = size.width - fadeWidth
        drawRect(
            brush = Brush.horizontalGradient(
                0f to Color.White,
                1f to Color.Transparent,
                startX = startX,
            ),
            topLeft = Offset(x = startX, y = 0f),
            blendMode = BlendMode.DstIn,
        )
    }
    bottomOffset.takeIf { it > 0.dp }?.toPx()?.let { fadeHeight ->
        val startY = size.height - fadeHeight
        drawRect(
            brush = Brush.verticalGradient(
                0f to Color.White,
                1f to Color.Transparent,
                startY = startY,
            ),
            topLeft = Offset(x = 0f, y = startY),
            blendMode = BlendMode.DstIn,
        )
    }
}

@Preview
@Composable
private fun Preview(
    @PreviewParameter(SampleFadingEdgesOffsetProvider::class)
    offset: PaddingValues,
) {
    Box(
        modifier = Modifier
            .size(100.dp)
            .fadingEdges(offset)
            .background(Color.Blue),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
                .background(Color.Yellow),
        )
    }
}

private class SampleFadingEdgesOffsetProvider : PreviewParameterProvider<PaddingValues> {

    override val values: Sequence<PaddingValues> = sequenceOf(
        PaddingValues(start = 20.dp),
        PaddingValues(top = 20.dp),
        PaddingValues(end = 20.dp),
        PaddingValues(bottom = 20.dp),
        PaddingValues(horizontal = 20.dp),
        PaddingValues(vertical = 20.dp),
        PaddingValues(all = 20.dp),
    )
}
