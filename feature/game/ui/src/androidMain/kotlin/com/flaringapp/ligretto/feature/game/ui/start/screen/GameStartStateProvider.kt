package com.flaringapp.ligretto.feature.game.ui.start.screen

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.flaringapp.ligretto.feature.game.ui.start.GameStartState

internal class GameStartStateProvider : PreviewParameterProvider<GameStartState> {

    override val values: Sequence<GameStartState> = sequenceOf(
        GameStartState(
            players = GameStartPlayersProvider.empty(),
            endConditions = GameStartState.EndConditions(),
        ),
        GameStartState(
            players = GameStartPlayersProvider.short(),
            endConditions = GameStartState.EndConditions(),
        ),
        GameStartState(
            players = GameStartPlayersProvider.long(),
            endConditions = GameStartState.EndConditions(),
        ),
    )
}
