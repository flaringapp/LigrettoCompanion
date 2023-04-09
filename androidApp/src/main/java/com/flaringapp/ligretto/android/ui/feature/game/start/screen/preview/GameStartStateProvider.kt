package com.flaringapp.ligretto.android.ui.feature.game.start.screen.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.flaringapp.ligretto.android.ui.feature.game.start.GameStartState

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
