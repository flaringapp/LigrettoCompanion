package com.flaringapp.ligretto.feature.game.ui.settings

import androidx.compose.runtime.Composable
import com.flaringapp.ligretto.core.arch.ConsumeEffects
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun GameSettingsDialog(
    close: () -> Unit,
    store: GameSettingsViewModel = koinViewModel(),
) {
    ConsumeEffects(store.observeEffect()) { effect ->
        when (effect) {
            GameSettingsEffect.Close -> close()
        }
    }
}
