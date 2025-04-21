package com.flaringapp.ligretto.feature.game.ui.settings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.flaringapp.ligretto.core.arch.ConsumeEffects
import com.flaringapp.ligretto.feature.game.ui.settings.screen.GameSettingsDialogContent
import org.koin.compose.viewmodel.koinViewModel
import kotlinx.serialization.Serializable

@Serializable
internal data object GameSettingsDestination

@Composable
internal fun GameSettingsDialog(
    close: () -> Unit,
    store: GameSettingsViewModel = koinViewModel(),
) {
    val state by store.observeState().collectAsStateWithLifecycle()

    ConsumeEffects(store.observeEffect()) { effect ->
        when (effect) {
            GameSettingsEffect.Close -> close()
        }
    }

    GameSettingsDialogContent(
        state = state,
        dispatch = store::dispatch,
        close = close,
    )
}
