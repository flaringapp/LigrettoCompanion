package com.flaringapp.ligretto.android.ui.feature.game.start.screen.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.flaringapp.ligretto.android.ui.feature.game.start.GameStartState

class GameStartStateProvider : PreviewParameterProvider<GameStartState> {

    override val values: Sequence<GameStartState> = GameStartPlayersProvider().values.map {
        GameStartState(players = it)
    }
}
