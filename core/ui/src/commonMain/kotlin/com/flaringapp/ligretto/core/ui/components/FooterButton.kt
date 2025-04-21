package com.flaringapp.ligretto.core.ui.components

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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.coerceAtLeast
import androidx.compose.ui.unit.dp
import com.flaringapp.ligretto.core.designsystem.AppTheme
import com.flaringapp.ligretto.core.ui.ext.screen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun FooterButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    horizontalPadding: Dp = 16.dp,
    bottomPadding: Dp = 48.dp,
    minimumBottomSpacingToWindowInsets: Dp = 16.dp,
    windowInsets: WindowInsets =
        WindowInsets.screen.only(WindowInsetsSides.Horizontal + WindowInsetsSides.Bottom),
    content: @Composable RowScope.() -> Unit,
) {
    val windowInsetsPadding = windowInsets.asPaddingValues()
    val extraBottomPadding = (bottomPadding - windowInsetsPadding.calculateBottomPadding())
        .coerceAtLeast(minimumBottomSpacingToWindowInsets)

    Button(
        modifier = modifier
            .fillMaxWidth()
            .padding(windowInsetsPadding)
            .padding(
                bottom = extraBottomPadding,
                start = horizontalPadding,
                end = horizontalPadding,
            )
            .consumeWindowInsets(windowInsets),
        onClick = onClick,
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp),
        content = content,
    )
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        FooterButton(
            onClick = {},
        ) {
            Text("Footer Button")
        }
    }
}
