package com.flaringapp.ligretto.android.ui.feature.game.start.screen.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.flaringapp.ligretto.android.ui.feature.game.start.GameStartState.Player
import com.flaringapp.ligretto.android.ui.feature.game.start.GameStartState.Players

class GameStartPlayersProvider : PreviewParameterProvider<Players> {

    override val values: Sequence<Players> = sequenceOf(
        Players(),
        Players(
            list = listOf(
                Player(1, "Andreo"),
                Player(2, "Mario"),
                Player(3, "Olenkka"),
            ),
        ),
        Players(
            list = listOf(
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
