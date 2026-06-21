package com.flaringapp.ligretto.feature.game.ui.start.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.flaringapp.ligretto.core.ui.components.UiPlayerAvatarType
import com.flaringapp.ligretto.core.ui.ext.uiListOf
import com.flaringapp.ligretto.feature.game.ui.start.GameStartState.Player
import com.flaringapp.ligretto.feature.game.ui.start.GameStartState.PlayerId
import com.flaringapp.ligretto.feature.game.ui.start.GameStartState.Players

internal class GameStartPlayersProvider : PreviewParameterProvider<Players> {

    companion object {
        fun empty() = Players()

        fun short() = Players(
            list = uiListOf(
                Player(PlayerId.Existing(1), "Andreo", UiPlayerAvatarType.Scout),
                Player(PlayerId.Existing(2), "Mario", UiPlayerAvatarType.Goober),
                Player(PlayerId.Existing(3), "Olenkka", null),
            ),
        )

        fun long() = Players(
            list = uiListOf(
                Player(PlayerId.Existing(1), "Brad", UiPlayerAvatarType.Corky),
                Player(PlayerId.Existing(2), "Lucio", UiPlayerAvatarType.Leo),
                Player(PlayerId.Existing(3), "Helen", UiPlayerAvatarType.Fluffy),
                Player(PlayerId.Existing(4), "Kate", UiPlayerAvatarType.Pip),
                Player(PlayerId.Existing(5), "Tony", UiPlayerAvatarType.Rocky),
                Player(PlayerId.Existing(6), "Tom", null),
                Player(PlayerId.Existing(7), "Mark", UiPlayerAvatarType.Dax),
                Player(PlayerId.New(1), "Dan", UiPlayerAvatarType.Patch),
                Player(PlayerId.New(2), "May", UiPlayerAvatarType.Mop),
                Player(PlayerId.New(3), "Jordan", null),
            ),
        )
    }

    override val values: Sequence<Players> = sequenceOf(
        empty(),
        short(),
        long(),
    )
}
