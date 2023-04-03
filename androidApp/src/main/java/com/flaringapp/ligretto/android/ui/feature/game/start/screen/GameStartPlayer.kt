package com.flaringapp.ligretto.android.ui.feature.game.start.screen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.flaringapp.ligretto.android.R
import com.flaringapp.ligretto.android.ui.AppTheme

@Composable
fun GameStartPlayer(
    name: String,
    isFocused: Boolean,
    onNameChanged: (String) -> Unit,
    onFocusChanged: (Boolean) -> Unit,
    onRemoveClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.padding(start = 16.dp, top = 12.dp, bottom = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        PlayerNameInput(
            name = name,
            isFocused = isFocused,
            onNameChanged = onNameChanged,
            onFocusChanged = onFocusChanged,
        )

        IconButton(
            onClick = onRemoveClick,
        ) {
            Icon(
                painter = rememberVectorPainter(Icons.Rounded.Delete),
                contentDescription = stringResource(R.string.players_player_remove),
                tint = MaterialTheme.colorScheme.error,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PlayerNameInput(
    name: String,
    isFocused: Boolean,
    onNameChanged: (String) -> Unit,
    onFocusChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    val focusRequester = remember { FocusRequester() }

    OutlinedTextField(
        modifier = modifier
            .focusRequester(focusRequester)
            .onFocusChanged {
                onFocusChanged(it.isFocused)
            },
        value = name,
        onValueChange = onNameChanged,
        placeholder = {
            Text(
                text = stringResource(R.string.players_player_placeholder),
            )
        },
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Words,
            imeAction = ImeAction.Done,
        ),
        singleLine = true,
    )

    LaunchedEffect(isFocused) {
        if (isFocused) focusRequester.requestFocus()
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewEmpty() {
    AppTheme {
        GameStartPlayer(
            name = "",
            isFocused = false,
            onNameChanged = {},
            onFocusChanged = {},
            onRemoveClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewFilled() {
    AppTheme {
        GameStartPlayer(
            name = "Player",
            isFocused = true,
            onNameChanged = {},
            onFocusChanged = {},
            onRemoveClick = {},
        )
    }
}
