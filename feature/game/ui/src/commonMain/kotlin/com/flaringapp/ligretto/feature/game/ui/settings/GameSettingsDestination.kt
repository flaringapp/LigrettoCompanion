package com.flaringapp.ligretto.feature.game.ui.settings

import androidx.compose.ui.window.DialogProperties
import com.flaringapp.ligretto.core.navigation.DialogDestination
import com.flaringapp.ligretto.core.navigation.ScreenDestinationWithoutArguments

internal object GameSettingsDestination : ScreenDestinationWithoutArguments(), DialogDestination {

    override val screenId: String = "game/settings"

    override val dialogProperties: DialogProperties = DialogProperties(
        dismissOnClickOutside = false,
    )
}
