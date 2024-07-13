package com.flaringapp.ligretto.core.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.flaringapp.ligretto.core.designsystem.AppTheme
import com.flaringapp.ligretto.core.ui.ext.screen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun FooterButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    outerPadding: PaddingValues = PaddingValues(bottom = 48.dp, start = 16.dp, end = 16.dp),
    windowInsets: WindowInsets =
        WindowInsets.screen.only(WindowInsetsSides.Horizontal + WindowInsetsSides.Bottom),
    content: @Composable RowScope.() -> Unit,
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .padding(outerPadding)
            .windowInsetsPadding(windowInsets),
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
