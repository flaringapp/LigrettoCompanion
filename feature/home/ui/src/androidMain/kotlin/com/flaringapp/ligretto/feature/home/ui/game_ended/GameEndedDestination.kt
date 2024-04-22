package com.flaringapp.ligretto.feature.home.ui.game_ended

import androidx.compose.ui.window.DialogProperties
import com.flaringapp.ligretto.core.navigation.DialogDestination
import com.flaringapp.ligretto.core.navigation.ScreenDestinationWithoutArguments

internal object GameEndedDestination : ScreenDestinationWithoutArguments(), DialogDestination {

    override val screenId: String = "home/game-ended"

    override val dialogProperties: DialogProperties = DialogProperties()
}
