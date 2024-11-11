package com.flaringapp.ligretto.feature.game.ui.common.menu

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import ligretto_companion.core.ui.generated.resources.overflow_description
import ligretto_companion.feature.game.ui.generated.resources.Res
import ligretto_companion.feature.game.ui.generated.resources.game_menu_finish_button
import org.jetbrains.compose.resources.stringResource
import ligretto_companion.core.ui.generated.resources.Res as CoreRes

@Composable
internal fun GameInProgressTopBarOverflowIconWithMenu(
    onFinishGameClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var showOverflowMenu by remember { mutableStateOf(false) }

    IconButton(
        modifier = modifier,
        onClick = { showOverflowMenu = !showOverflowMenu },
    ) {
        Icon(
            imageVector = Icons.Rounded.MoreVert,
            contentDescription = stringResource(CoreRes.string.overflow_description),
        )
    }

    DropdownMenu(
        expanded = showOverflowMenu,
        onDismissRequest = { showOverflowMenu = false },
    ) {
        DropdownMenuItem(
            onClick = onFinishGameClick,
            text = {
                Text(text = stringResource(Res.string.game_menu_finish_button))
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.CheckCircle,
                    contentDescription = null,
                )
            },
        )
    }
}
