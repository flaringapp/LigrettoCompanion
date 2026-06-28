package com.flaringapp.ligretto.feature.game.ui.start.screen

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.FloatState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.asFloatState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.flaringapp.ligretto.core.designsystem.AppTheme
import com.flaringapp.ligretto.core.ui.components.player.image.PlayerAvatarImage
import com.flaringapp.ligretto.core.ui.components.player.image.PlayerImageDefaults
import com.flaringapp.ligretto.core.ui.components.player.image.PlayerNameImage
import com.flaringapp.ligretto.core.ui.components.player.image.UiPlayerAvatarType
import com.flaringapp.ligretto.core.ui.ext.UiList
import com.flaringapp.ligretto.core.ui.ext.UnboundedPaddingLayout
import com.flaringapp.ligretto.core.ui.ext.fadingEdges
import com.flaringapp.ligretto.core.ui.ext.uiListOf
import ligretto_companion.feature.game.ui.generated.resources.Res
import ligretto_companion.feature.game.ui.generated.resources.start_player_avatar_picker_close
import ligretto_companion.feature.game.ui.generated.resources.start_player_avatar_picker_title
import org.jetbrains.compose.resources.stringResource

private val AvatarPickerSelectedRingWidth = 3.dp
private val AvatarPickerSelectedRingGap = 4.dp

private const val TYPE_NO_AVATAR = "no_avatar"
private const val TYPE_AVATAR = "avatar"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AvatarPickerDialog(
    currentAvatar: UiPlayerAvatarType?,
    playerName: String,
    playerNameFallback: String,
    onSelect: (UiPlayerAvatarType?) -> Unit,
    onDismiss: () -> Unit,
) {
    var newSelection: AvatarSelection? by remember { mutableStateOf(null) }

    newSelection?.let {
        LaunchedEffect(Unit) {
            onSelect(it.avatar)
        }
    }

    BasicAlertDialog(onDismissRequest = onDismiss) {
        Surface(
            shape = AlertDialogDefaults.shape,
            color = AlertDialogDefaults.containerColor,
            tonalElevation = AlertDialogDefaults.TonalElevation,
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 24.dp),
            ) {
                Header(
                    modifier = Modifier.padding(top = 24.dp, bottom = 12.dp),
                    onCloseClick = onDismiss,
                )

                AvatarPickerGrid(
                    currentAvatar = if (newSelection != null) {
                        newSelection?.avatar
                    } else {
                        currentAvatar
                    },
                    playerName = playerName,
                    playerNameFallback = playerNameFallback,
                    onSelect = { newSelection = AvatarSelection(it) },
                )
            }
        }
    }
}

@Composable
private fun Header(
    onCloseClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = stringResource(Res.string.start_player_avatar_picker_title),
            style = MaterialTheme.typography.titleLarge,
        )

        UnboundedPaddingLayout(
            padding = PaddingValues(12.dp),
        ) {
            IconButton(
                onClick = onCloseClick,
            ) {
                Icon(
                    imageVector = Icons.Rounded.Close,
                    contentDescription = stringResource(
                        Res.string.start_player_avatar_picker_close,
                    ),
                )
            }
        }
    }
}

@Composable
private fun AvatarPickerGrid(
    currentAvatar: UiPlayerAvatarType?,
    playerName: String,
    playerNameFallback: String,
    onSelect: (UiPlayerAvatarType?) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        modifier = modifier
            .fadingEdges(PaddingValues(vertical = 12.dp)),
        columns = GridCells.Adaptive(minSize = 68.dp),
        contentPadding = PaddingValues(top = 12.dp, bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        item(key = TYPE_NO_AVATAR, contentType = TYPE_NO_AVATAR) {
            SelectableAvatar(
                selected = currentAvatar == null,
                onClick = { onSelect(null) },
            ) {
                PlayerNameImage(
                    name = playerName,
                    fallbackText = playerNameFallback,
                )
            }
        }

        items(
            items = UiPlayerAvatarType.entries,
            key = { "${TYPE_AVATAR}_$it" },
            contentType = { TYPE_AVATAR },
        ) { avatar ->
            SelectableAvatar(
                selected = avatar == currentAvatar,
                onClick = { onSelect(avatar) },
            ) {
                PlayerAvatarImage(
                    avatar = avatar,
                )
            }
        }
    }
}

@Composable
private fun SelectableAvatar(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = PlayerImageDefaults.Shape,
    avatar: @Composable () -> Unit,
) {
    val selectedRingModifier = if (selected) {
        val ringRotationState = rememberAnimatedSelectionRingRotationState()
        Modifier.animatedSelectionRing(
            width = AvatarPickerSelectedRingWidth,
            colors = with(MaterialTheme.colorScheme) {
                uiListOf(primary, secondary, secondary, primary)
            },
            shape = shape,
            rotationProvider = { ringRotationState.floatValue },
        )
    } else {
        Modifier
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f, matchHeightConstraintsFirst = false)
            .then(selectedRingModifier)
            .padding(AvatarPickerSelectedRingWidth + AvatarPickerSelectedRingGap)
            .clip(shape)
            .selectable(
                selected = selected,
                onClick = onClick,
                role = Role.RadioButton,
            ),
        contentAlignment = Alignment.Center,
        propagateMinConstraints = true,
    ) {
        avatar()
    }
}

@Composable
private fun rememberAnimatedSelectionRingRotationState(): FloatState {
    val transition = rememberInfiniteTransition(label = "AvatarPickerSelectionRingTransition")
    return transition.animateFloat(
        label = "AvatarPickerSelectionRingRotation",
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        ),
    ).asFloatState()
}

private fun Modifier.animatedSelectionRing(
    width: Dp,
    colors: UiList<Color>,
    shape: Shape,
    rotationProvider: () -> Float,
): Modifier = drawWithCache {
    val strokeWidthPx = width.toPx()
    val ringInsetPx = strokeWidthPx / 2f
    val outline = shape.createOutline(
        size = size.copy(
            width = size.width - strokeWidthPx,
            height = size.height - strokeWidthPx,
        ),
        layoutDirection = layoutDirection,
        density = this,
    )
    val brush = Brush.sweepGradient(colors)

    onDrawWithContent {
        drawContent()
        withTransform(
            transformBlock = {
                inset(ringInsetPx)
                rotate(
                    degrees = rotationProvider(),
                    pivot = center,
                )
            },
        ) {
            drawOutline(
                outline = outline,
                brush = brush,
                style = Stroke(width = strokeWidthPx),
            )
        }
    }
}

private data class AvatarSelection(
    val avatar: UiPlayerAvatarType?,
)

@Preview
@Composable
private fun PreviewNoSelected() {
    AppTheme {
        AvatarPickerDialog(
            currentAvatar = null,
            playerName = "Andreo",
            playerNameFallback = "1",
            onSelect = {},
            onDismiss = {},
        )
    }
}

@Preview
@Composable
private fun PreviewSomeSelected() {
    AppTheme {
        AvatarPickerDialog(
            currentAvatar = UiPlayerAvatarType.Scout,
            playerName = "Andreo",
            playerNameFallback = "1",
            onSelect = {},
            onDismiss = {},
        )
    }
}
