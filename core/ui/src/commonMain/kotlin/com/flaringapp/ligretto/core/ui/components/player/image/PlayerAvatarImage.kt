package com.flaringapp.ligretto.core.ui.components.player.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.flaringapp.ligretto.core.designsystem.AppTheme
import com.flaringapp.ligretto.core.ui.ext.UiList
import com.flaringapp.ligretto.core.ui.ext.uiListOf
import ligretto_companion.core.ui.generated.resources.Res
import ligretto_companion.core.ui.generated.resources.avatar_benny
import ligretto_companion.core.ui.generated.resources.avatar_coco
import ligretto_companion.core.ui.generated.resources.avatar_corky
import ligretto_companion.core.ui.generated.resources.avatar_dax
import ligretto_companion.core.ui.generated.resources.avatar_dot
import ligretto_companion.core.ui.generated.resources.avatar_earl
import ligretto_companion.core.ui.generated.resources.avatar_fluffy
import ligretto_companion.core.ui.generated.resources.avatar_fritz
import ligretto_companion.core.ui.generated.resources.avatar_goober
import ligretto_companion.core.ui.generated.resources.avatar_leo
import ligretto_companion.core.ui.generated.resources.avatar_mop
import ligretto_companion.core.ui.generated.resources.avatar_patch
import ligretto_companion.core.ui.generated.resources.avatar_pip
import ligretto_companion.core.ui.generated.resources.avatar_rex
import ligretto_companion.core.ui.generated.resources.avatar_rocky
import ligretto_companion.core.ui.generated.resources.avatar_scout
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun PlayerAvatarImage(
    avatar: UiPlayerAvatarType,
    size: Dp,
    modifier: Modifier = Modifier,
    shape: Shape = PlayerImageDefaults.Shape,
) {
    val gradient = Brush.verticalGradient(avatar.backgroundGradientColors)

    Box(
        modifier = modifier
            .size(size)
            .clip(shape)
            .background(gradient),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            modifier = Modifier.fillMaxSize(0.8f),
            painter = painterResource(avatar.imageRes),
            contentDescription = null,
        )
    }
}

sealed class UiPlayerAvatarType {
    abstract val imageRes: DrawableResource
    abstract val backgroundGradientColors: UiList<Color>

    data object Goober : UiPlayerAvatarType() {
        override val imageRes get() = Res.drawable.avatar_goober
        override val backgroundGradientColors = uiListOf(Color(0xFFFFDDB0), Color(0xFFFF9050))
    }

    data object Scout : UiPlayerAvatarType() {
        override val imageRes get() = Res.drawable.avatar_scout
        override val backgroundGradientColors = uiListOf(Color(0xFFC8EEF8), Color(0xFF50B4D8))
    }

    data object Corky : UiPlayerAvatarType() {
        override val imageRes get() = Res.drawable.avatar_corky
        override val backgroundGradientColors = uiListOf(Color(0xFFD8C4F4), Color(0xFFF098C0))
    }

    data object Leo : UiPlayerAvatarType() {
        override val imageRes get() = Res.drawable.avatar_leo
        override val backgroundGradientColors = uiListOf(Color(0xFFB8D8F0), Color(0xFFEE9070))
    }

    data object Dax : UiPlayerAvatarType() {
        override val imageRes get() = Res.drawable.avatar_dax
        override val backgroundGradientColors = uiListOf(Color(0xFFEEDCC0), Color(0xFFBB8448))
    }

    data object Dot : UiPlayerAvatarType() {
        override val imageRes get() = Res.drawable.avatar_dot
        override val backgroundGradientColors = uiListOf(Color(0xFFD0EEE4), Color(0xFF68BC9C))
    }

    data object Fluffy : UiPlayerAvatarType() {
        override val imageRes get() = Res.drawable.avatar_fluffy
        override val backgroundGradientColors = uiListOf(Color(0xFFFAD4E0), Color(0xFFE85890))
    }

    data object Fritz : UiPlayerAvatarType() {
        override val imageRes get() = Res.drawable.avatar_fritz
        override val backgroundGradientColors = uiListOf(Color(0xFFD4E0F4), Color(0xFF6898D4))
    }

    data object Benny : UiPlayerAvatarType() {
        override val imageRes get() = Res.drawable.avatar_benny
        override val backgroundGradientColors = uiListOf(Color(0xFFFFE0B8), Color(0xFFFF8C38))
    }

    data object Coco : UiPlayerAvatarType() {
        override val imageRes get() = Res.drawable.avatar_coco
        override val backgroundGradientColors = uiListOf(Color(0xFFFFF4C0), Color(0xFFFFBE20))
    }

    data object Patch : UiPlayerAvatarType() {
        override val imageRes get() = Res.drawable.avatar_patch
        override val backgroundGradientColors = uiListOf(Color(0xFFB0D4F0), Color(0xFFEE9880))
    }

    data object Rex : UiPlayerAvatarType() {
        override val imageRes get() = Res.drawable.avatar_rex
        override val backgroundGradientColors = uiListOf(Color(0xFFD0DCF4), Color(0xFF7888C4))
    }

    data object Earl : UiPlayerAvatarType() {
        override val imageRes get() = Res.drawable.avatar_earl
        override val backgroundGradientColors = uiListOf(Color(0xFFFFECC8), Color(0xFFFF9C38))
    }

    data object Rocky : UiPlayerAvatarType() {
        override val imageRes get() = Res.drawable.avatar_rocky
        override val backgroundGradientColors = uiListOf(Color(0xFFEED8CC), Color(0xFFCC7060))
    }

    data object Mop : UiPlayerAvatarType() {
        override val imageRes get() = Res.drawable.avatar_mop
        override val backgroundGradientColors = uiListOf(Color(0xFFE0D4F8), Color(0xFF8864D4))
    }

    data object Pip : UiPlayerAvatarType() {
        override val imageRes get() = Res.drawable.avatar_pip
        override val backgroundGradientColors = uiListOf(Color(0xFFD4F4DC), Color(0xFF60C07C))
    }

    companion object {

        val entries: UiList<UiPlayerAvatarType> = uiListOf(
            Goober, Scout, Corky, Leo, Dax, Dot, Fluffy, Fritz,
            Benny, Coco, Patch, Rex, Earl, Rocky, Mop, Pip,
        )
    }
}

@Preview
@Composable
private fun PreviewGoober() {
    AppTheme {
        PlayerAvatarImage(
            avatar = UiPlayerAvatarType.Goober,
            size = 64.dp,
        )
    }
}

@Preview
@Composable
private fun PreviewScout() {
    AppTheme {
        PlayerAvatarImage(
            avatar = UiPlayerAvatarType.Scout,
            size = 64.dp,
        )
    }
}
