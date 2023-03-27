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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.flaringapp.ligretto.android.AppTheme
import com.flaringapp.ligretto.android.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameStartPlayer(
    name: String,
    onNameChanged: (String) -> Unit,
    onRemoveClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.padding(start = 16.dp, top = 12.dp, bottom = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        OutlinedTextField(
            modifier = Modifier.weight(1f),
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

@Preview(showBackground = true)
@Composable
private fun PreviewEmpty() {
    AppTheme {
        GameStartPlayer(
            name = "",
            onNameChanged = {},
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
            onNameChanged = {},
            onRemoveClick = {},
        )
    }
}
