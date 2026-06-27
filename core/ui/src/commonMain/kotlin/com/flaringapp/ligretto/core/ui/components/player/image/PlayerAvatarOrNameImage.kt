package com.flaringapp.ligretto.core.ui.components.player.image

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.flaringapp.ligretto.core.designsystem.AppTheme

@Composable
fun PlayerAvatarOrNameImage(
    avatar: UiPlayerAvatarType?,
    name: String,
    size: Dp,
    modifier: Modifier = Modifier,
    fallbackText: String = "?",
    shape: Shape = PlayerImageDefaults.Shape,
) {
    if (avatar != null) {
        PlayerAvatarImage(
            modifier = modifier,
            avatar = avatar,
            size = size,
            shape = shape,
        )
    } else {
        PlayerNameImage(
            modifier = modifier,
            name = name,
            fallbackText = fallbackText,
            size = size,
            shape = shape,
        )
    }
}

@Preview
@Composable
private fun PreviewWithAvatar() {
    AppTheme {
        PlayerAvatarOrNameImage(
            avatar = UiPlayerAvatarType.Scout,
            name = "Andreo",
            size = 64.dp,
        )
    }
}

@Preview
@Composable
private fun PreviewWithName() {
    AppTheme {
        PlayerAvatarOrNameImage(
            avatar = null,
            name = "Andreo",
            size = 64.dp,
        )
    }
}
