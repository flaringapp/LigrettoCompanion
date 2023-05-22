package com.flaringapp.ligretto.feature.game.ui.start.screen.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.flaringapp.ligretto.feature.game.ui.start.GameStartState.Player
import com.flaringapp.ligretto.feature.game.ui.start.GameStartState.Players

internal class GameStartPlayersProvider : PreviewParameterProvider<Players> {

    companion object {
        fun empty() = Players()

        fun short() = Players(
            list = listOf(
                Player(1, "Andreo"),
                Player(2, "Mario"),
                Player(3, "Olenkka"),
            ),
        )

        fun long() = Players(
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
    }

    override val values: Sequence<Players> = sequenceOf(
        empty(),
        short(),
        long(),
    )
}
