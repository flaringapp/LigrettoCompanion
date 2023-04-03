package com.flaringapp.ligretto.android.ui.feature.game.close

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.DialogProperties
import com.flaringapp.ligretto.android.R
import com.flaringapp.ligretto.android.ui.AppTheme
import com.flaringapp.ligretto.android.ui.mvi.ConsumeEffects
import com.flaringapp.ligretto.android.ui.utils.navigation.DialogDestination
import com.flaringapp.ligretto.android.ui.utils.navigation.ScreenDestinationWithoutArguments
import org.koin.androidx.compose.getViewModel

object GameCloseDestination : ScreenDestinationWithoutArguments(), DialogDestination {

    override val screenId: String = "game/close"

    override val dialogProperties: DialogProperties = DialogProperties()
}

@Composable
fun GameCloseDialog(
    openEnd: () -> Unit,
    closeGame: () -> Unit,
    dismiss: () -> Unit,
    store: GameCloseViewModel = getViewModel(),
) {
    ConsumeEffects(store.observeEffect()) { effect ->
        when (effect) {
            GameCloseEffect.EndGame -> openEnd()
            GameCloseEffect.CloseGame -> closeGame()
            GameCloseEffect.Dismiss -> dismiss()
        }
    }

    GameCloseDialogContent(
        approve = { store.dispatch(GameCloseIntent.Approve) },
        dismiss = { store.dispatch(GameCloseIntent.Dismiss) },
    )
}

@Composable
private fun GameCloseDialogContent(
    approve: () -> Unit,
    dismiss: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = dismiss,
        title = {
            Text(
                text = stringResource(R.string.game_close_title),
                style = MaterialTheme.typography.titleMedium,
            )
        },
        text = {
            Text(text = stringResource(R.string.game_close_message))
        },
        confirmButton = {
            TextButton(onClick = approve) {
                Text(text = stringResource(R.string.yes))
            }
        },
        dismissButton = {
            TextButton(onClick = dismiss) {
                Text(text = stringResource(R.string.cancel))
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    AppTheme {
        GameCloseDialogContent(
            approve = {},
            dismiss = {},
        )
    }
}
