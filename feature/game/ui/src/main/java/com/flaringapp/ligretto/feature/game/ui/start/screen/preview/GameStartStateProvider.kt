package com.flaringapp.ligretto.feature.game.ui.start.screen.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.flaringapp.ligretto.feature.game.ui.start.GameStartState

class GameStartStateProvider : PreviewParameterProvider<GameStartState> {

    override val values: Sequence<GameStartState> = sequenceOf(
        GameStartState(
            players = GameStartPlayersProvider.empty(),
            GameStartEndConditionsProvider.none(),
        ),
        GameStartState(
            players = GameStartPlayersProvider.short(),
            GameStartEndConditionsProvider.scoreOnly(),
        ),
        GameStartState(
            players = GameStartPlayersProvider.long(),
            GameStartEndConditionsProvider.all(),
        ),
    )
}
