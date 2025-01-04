package com.flaringapp.ligretto.feature.game.ui.close

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import com.flaringapp.ligretto.core.arch.ConsumeEffects
import ligretto_companion.core.ui.generated.resources.cancel
import ligretto_companion.core.ui.generated.resources.yes
import ligretto_companion.feature.game.ui.generated.resources.Res
import ligretto_companion.feature.game.ui.generated.resources.game_close_message
import ligretto_companion.feature.game.ui.generated.resources.game_close_title
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import kotlinx.serialization.Serializable
import ligretto_companion.core.ui.generated.resources.Res as CoreRes

@Serializable
internal data object GameCloseDestination

@Composable
internal fun GameCloseDialog(
    openEnd: () -> Unit,
    closeGame: () -> Unit,
    dismiss: () -> Unit,
    store: GameCloseViewModel = koinViewModel(),
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
fun GameCloseDialogContent(
    approve: () -> Unit,
    dismiss: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = dismiss,
        title = {
            Text(
                text = stringResource(Res.string.game_close_title),
                style = MaterialTheme.typography.titleMedium,
            )
        },
        text = {
            Text(text = stringResource(Res.string.game_close_message))
        },
        confirmButton = {
            TextButton(onClick = approve) {
                Text(text = stringResource(CoreRes.string.yes))
            }
        },
        dismissButton = {
            TextButton(onClick = dismiss) {
                Text(text = stringResource(CoreRes.string.cancel))
            }
        },
    )
}
