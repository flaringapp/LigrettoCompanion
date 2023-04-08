package com.flaringapp.ligretto.android.ui.feature.game.start.screen.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.flaringapp.ligretto.android.ui.feature.game.start.GameStartState
import com.flaringapp.ligretto.android.ui.feature.game.start.GameStartState.Player

class GameStartStateProvider : PreviewParameterProvider<GameStartState> {

    override val values: Sequence<GameStartState> = sequenceOf(
        GameStartState(),
        GameStartState(
            players = listOf(
                Player(1, "Andreo"),
                Player(2, "Mario"),
                Player(3, "Olenkka"),
            ),
        ),
        GameStartState(
            players = listOf(
                Player(1, "Brad"),
                Player(2, "Lucio"),
                Player(3, "Helen"),
                Player(4, "Kate"),
                Player(5, "Tony"),
                Player(6, "Tom"),
                Player(7, "Mark"),
                Player(8, "Dan"),
                Player(9, "May"),
                Player(10, "Jordan"),
            ),
        )
    )
}
