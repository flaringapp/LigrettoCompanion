package com.flaringapp.ligretto.feature.game.ui.start.screen

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.RemoveCircleOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import com.flaringapp.ligretto.core.ui.ext.UnboundedPaddingLayout
import com.flaringapp.ligretto.feature.game.ui.common.GamePlayerImage
import ligretto_companion.feature.game.ui.generated.resources.Res
import ligretto_companion.feature.game.ui.generated.resources.start_player_hint
import ligretto_companion.feature.game.ui.generated.resources.start_player_remove
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun GameStartPlayer(
    name: String,
    number: Int,
    canRemove: Boolean,
    requestFocus: Boolean,
    onNameChange: (String) -> Unit,
    onFocusChanged: (Boolean) -> Unit,
    onRemoveClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        GamePlayerImage(
            modifier = Modifier.padding(end = 8.dp),
            name = name,
            fallbackText = number.toString(),
            size = 56.dp,
        )

        PlayerNameTextField(
            modifier = Modifier.weight(1f),
            name = name,
            number = number,
            requestFocus = requestFocus,
            onNameChange = onNameChange,
            onFocusChanged = onFocusChanged,
        )

        AnimatedRemoveButton(
            visible = canRemove,
            onClick = onRemoveClick,
        )
    }
}

@Composable
private fun PlayerNameTextField(
    name: String,
    number: Int,
    requestFocus: Boolean,
    onNameChange: (String) -> Unit,
    onFocusChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    val focusRequester = remember { FocusRequester() }

    TextField(
        modifier = modifier
            .focusRequester(focusRequester)
            .onFocusChanged {
                onFocusChanged(it.isFocused)
            },
        value = name,
        onValueChange = onNameChange,
        label = {
            Text(
                text = stringResource(Res.string.start_player_hint, number),
            )
        },
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Words,
            imeAction = ImeAction.Done,
        ),
        singleLine = true,
    )

    LaunchedEffect(requestFocus) {
        if (requestFocus) focusRequester.requestFocus()
    }
}

@Composable
private fun AnimatedRemoveButton(
    visible: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    AnimatedContent(
        modifier = modifier,
        label = "RemoveButtonVisibilityAnimation",
        targetState = onClick.takeIf { visible },
        contentKey = { it == null },
        transitionSpec = {
            expandHorizontally(
                expandFrom = Alignment.Start,
                clip = false,
            ) togetherWith shrinkHorizontally(
                shrinkTowards = Alignment.Start,
                clip = false,
            ) using null
        },
    ) { currentOnClick ->
        if (currentOnClick == null) {
            return@AnimatedContent
        }

        RemoveButton(
            modifier = Modifier.padding(start = 8.dp),
            onClick = currentOnClick,
        )
    }
}

@Composable
private fun RemoveButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val padding = PaddingValues(horizontal = 4.dp)
    UnboundedPaddingLayout(
        modifier = modifier,
        padding = padding,
    ) {
        IconButton(
            onClick = onClick,
        ) {
            Icon(
                imageVector = Icons.Rounded.RemoveCircleOutline,
                contentDescription = stringResource(Res.string.start_player_remove),
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}
