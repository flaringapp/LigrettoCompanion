package com.flaringapp.ligretto.feature.game.ui.start.screen

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.flaringapp.ligretto.core.ui.ext.uiListOf
import com.flaringapp.ligretto.feature.game.ui.start.GameStartState.Player
import com.flaringapp.ligretto.feature.game.ui.start.GameStartState.PlayerId
import com.flaringapp.ligretto.feature.game.ui.start.GameStartState.Players

internal class GameStartPlayersProvider : PreviewParameterProvider<Players> {

    companion object {
        fun empty() = Players()

        fun short() = Players(
            list = uiListOf(
                Player(PlayerId.Existing(1), "Andreo"),
                Player(PlayerId.Existing(2), "Mario"),
                Player(PlayerId.Existing(3), "Olenkka"),
            ),
        )

        fun long() = Players(
            list = uiListOf(
                Player(PlayerId.Existing(1), "Brad"),
                Player(PlayerId.Existing(2), "Lucio"),
                Player(PlayerId.Existing(3), "Helen"),
                Player(PlayerId.Existing(4), "Kate"),
                Player(PlayerId.Existing(5), "Tony"),
                Player(PlayerId.Existing(6), "Tom"),
                Player(PlayerId.Existing(7), "Mark"),
                Player(PlayerId.New(1), "Dan"),
                Player(PlayerId.New(2), "May"),
                Player(PlayerId.New(3), "Jordan"),
            ),
        )
    }

    override val values: Sequence<Players> = sequenceOf(
        empty(),
        short(),
        long(),
    )
}
