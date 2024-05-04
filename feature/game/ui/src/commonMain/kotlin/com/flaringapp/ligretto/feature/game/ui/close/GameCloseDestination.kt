package com.flaringapp.ligretto.feature.game.ui.close

import androidx.compose.ui.window.DialogProperties
import com.flaringapp.ligretto.core.navigation.DialogDestination
import com.flaringapp.ligretto.core.navigation.ScreenDestinationWithoutArguments

internal object GameCloseDestination : ScreenDestinationWithoutArguments(), DialogDestination {

    override val screenId: String = "game/close"

    override val dialogProperties: DialogProperties = DialogProperties()
}
