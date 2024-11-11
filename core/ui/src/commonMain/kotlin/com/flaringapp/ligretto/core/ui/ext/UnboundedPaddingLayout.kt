package com.flaringapp.ligretto.core.ui.ext

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.math.max

/**
 * Layout that allows to place a composable excluding its padding from measurements. E.g. when
 * you have a clickable node, and you don't want padded click area to expand parent size, then
 * you can use this layout. It'll position the element like it has no padding, but it'll be there
 * outside the node bounds.
 *
 * @param padding The padding of the [content].
 * @param modifier The modifier for the layout.
 * @param content The content to be placed excluding [padding] measurement.
 */
@Composable
inline fun UnboundedPaddingLayout(
    padding: PaddingValues,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Layout(
        modifier = modifier,
        content = content,
    ) { measurables, constraints ->
        val paddingTop = padding.calculateTopPadding().roundToPx()
        val paddingBottom = padding.calculateBottomPadding().roundToPx()
        val paddingLeft = padding.calculateLeftPadding(layoutDirection).roundToPx()
        val paddingRight = padding.calculateRightPadding(layoutDirection).roundToPx()

        val maxContentWidth = constraints.maxWidth.takeIf { it == Constraints.Infinity } ?: run {
            constraints.maxWidth + paddingLeft + paddingRight
        }
        val maxContentHeight = constraints.maxHeight.takeIf { it == Constraints.Infinity } ?: run {
            constraints.maxHeight + paddingTop + paddingBottom
        }
        val unboundedConstraints = constraints.copy(
            maxWidth = maxContentWidth,
            maxHeight = maxContentHeight,
        )

        val placeables = measurables.map { it.measure(unboundedConstraints) }

        val placeablesMaxWidth = placeables.maxOf { it.width }
        val placeablesMaxHeight = placeables.maxOf { it.height }

        val paddedContentWidth = max(0, placeablesMaxWidth - paddingLeft - paddingRight)
        val paddedContentHeight = max(0, placeablesMaxHeight - paddingTop - paddingBottom)

        layout(paddedContentWidth, paddedContentHeight) {
            placeables.forEach {
                it.place(
                    x = -paddingLeft,
                    y = -paddingTop,
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewRowWithContentAndPaddedElement() {
    Row(
        modifier = Modifier
            .padding(50.dp)
            .background(Color.LightGray),
    ) {
        Box(
            modifier = Modifier
                .size(width = 100.dp, height = 30.dp)
                .background(Color.Black),
        )

        val padding = PaddingValues(10.dp)
        UnboundedPaddingLayout(
            modifier = Modifier.background(Color.Green),
            padding = padding,
        ) {
            Box(
                modifier = Modifier
                    .background(Color.Yellow.copy(alpha = 0.5f))
                    .padding(padding)
                    .size(20.dp)
                    .background(Color.Red),
            )
        }
    }
}
