package com.flaringapp.ligretto.feature.game.ui.lap.start.screen

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.flaringapp.ligretto.feature.game.ui.lap.start.GameLapStartState

internal class GameLapStartStateProvider : PreviewParameterProvider<GameLapStartState> {

    override val values: Sequence<GameLapStartState> = sequenceOf(
        GameLapStartState(),
        GameLapStartState(lapNumber = 1),
        GameLapStartState(lapNumber = 2),
        GameLapStartState(lapNumber = 3),
        GameLapStartState(lapNumber = 7),
        GameLapStartState(lapNumber = 9),
        GameLapStartState(lapNumber = 10),
        GameLapStartState(lapNumber = 23),
        GameLapStartState(lapNumber = 863),
    )
}
