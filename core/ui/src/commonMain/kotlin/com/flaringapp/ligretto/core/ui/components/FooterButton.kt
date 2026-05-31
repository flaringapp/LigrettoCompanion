package com.flaringapp.ligretto.core.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.coerceAtLeast
import androidx.compose.ui.unit.dp
import com.flaringapp.ligretto.core.designsystem.AppTheme
import com.flaringapp.ligretto.core.ui.ext.screen

@Composable
fun FooterButtonInContainer(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    horizontalPadding: Dp = 16.dp,
    bottomPadding: Dp = 48.dp,
    minimumBottomSpacingToWindowInsets: Dp = 16.dp,
    windowInsets: WindowInsets =
        WindowInsets.screen.only(WindowInsetsSides.Horizontal + WindowInsetsSides.Bottom),
    content: @Composable RowScope.() -> Unit,
) {
    FooterButtonContainer(
        modifier = modifier,
        horizontalPadding = horizontalPadding,
        bottomPadding = bottomPadding,
        minimumBottomSpacingToWindowInsets = minimumBottomSpacingToWindowInsets,
        windowInsets = windowInsets,
    ) {
        FooterButton(
            onClick = onClick,
            content = content,
        )
    }
}

@Composable
fun FooterButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp),
        content = content,
    )
}

@Composable
inline fun FooterButtonContainer(
    modifier: Modifier = Modifier,
    horizontalPadding: Dp = 16.dp,
    bottomPadding: Dp = 48.dp,
    minimumBottomSpacingToWindowInsets: Dp = 16.dp,
    windowInsets: WindowInsets =
        WindowInsets.screen.only(WindowInsetsSides.Horizontal + WindowInsetsSides.Bottom),
    button: @Composable () -> Unit,
) {
    val windowInsetsPadding = windowInsets.asPaddingValues()
    val extraBottomPadding = (bottomPadding - windowInsetsPadding.calculateBottomPadding())
        .coerceAtLeast(minimumBottomSpacingToWindowInsets)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(windowInsetsPadding)
            .padding(
                bottom = extraBottomPadding,
                start = horizontalPadding,
                end = horizontalPadding,
            )
            .consumeWindowInsets(windowInsets),
        propagateMinConstraints = true,
    ) {
        button()
    }
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        FooterButtonInContainer(
            onClick = {},
        ) {
            Text("Footer Button")
        }
    }
}
