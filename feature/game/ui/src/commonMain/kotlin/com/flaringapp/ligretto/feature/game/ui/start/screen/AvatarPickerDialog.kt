package com.flaringapp.ligretto.feature.game.ui.start.screen

import androidx.compose.foundation.border
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.flaringapp.ligretto.core.designsystem.AppTheme
import com.flaringapp.ligretto.core.ui.components.player.image.PlayerAvatarImage
import com.flaringapp.ligretto.core.ui.components.player.image.PlayerImageDefaults
import com.flaringapp.ligretto.core.ui.components.player.image.PlayerNameImage
import com.flaringapp.ligretto.core.ui.components.player.image.UiPlayerAvatarType
import com.flaringapp.ligretto.core.ui.ext.UnboundedPaddingLayout
import com.flaringapp.ligretto.core.ui.ext.fadingEdges
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
                    currentAvatar = currentAvatar,
                    playerName = playerName,
                    playerNameFallback = playerNameFallback,
                    onSelect = onSelect,
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
                    contentDescription = stringResource(Res.string.start_player_avatar_picker_close),
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
        Modifier.border(
            width = AvatarPickerSelectedRingWidth,
            color = MaterialTheme.colorScheme.primary,
            shape = shape,
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
