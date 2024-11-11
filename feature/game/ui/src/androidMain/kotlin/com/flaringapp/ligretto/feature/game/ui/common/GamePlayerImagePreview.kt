package com.flaringapp.ligretto.feature.game.ui.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.flaringapp.ligretto.core.designsystem.AppTheme

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
        GamePlayerImage(
            modifier = modifier,
            size = 40.dp,
            name = name,
        )
    }
}
