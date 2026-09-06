package com.flaringapp.ligretto.core.ui.components.player.image

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.flaringapp.ligretto.core.designsystem.AppTheme

@Composable
fun PlayerAvatarOrNameImage(
    avatar: UiPlayerAvatarType?,
    name: String,
    modifier: Modifier = Modifier,
    fallbackText: String = "?",
    shape: Shape = PlayerImageDefaults.Shape,
) {
    if (avatar != null) {
        PlayerAvatarImage(
            modifier = modifier,
            avatar = avatar,
            shape = shape,
        )
    } else {
        PlayerNameImage(
            modifier = modifier,
            name = name,
            fallbackText = fallbackText,
            shape = shape,
        )
    }
}

@Preview
@Composable
private fun PreviewWithAvatar() {
    AppTheme {
        PlayerAvatarOrNameImage(
            modifier = Modifier.size(64.dp),
            avatar = UiPlayerAvatarType.Scout,
            name = "Andreo",
        )
    }
}

@Preview
@Composable
private fun PreviewWithName() {
    AppTheme {
        PlayerAvatarOrNameImage(
            modifier = Modifier.size(64.dp),
            avatar = null,
            name = "Andreo",
        )
    }
}
