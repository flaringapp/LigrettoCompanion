package com.flaringapp.ligretto.feature.game.ui.common.menu

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.flaringapp.ligretto.core.designsystem.AppTheme

@Preview(showBackground = true)
@Composable
private fun Preview() {
    AppTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            GameInProgressTopBarOverflowIconWithMenu(
                modifier = Modifier.align(Alignment.TopEnd),
                onFinishGameClick = {},
            )
        }
    }
}
