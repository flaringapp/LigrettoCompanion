package com.flaringapp.ligretto.feature.game.ui.score.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.flaringapp.ligretto.core.ui.components.UiPlayerAvatarType
import com.flaringapp.ligretto.feature.game.ui.score.GameScoreState.PlayerScore

internal class GameScorePlayerScoreProvider : PreviewParameterProvider<PlayerScore> {

    companion object {

        fun firstPlace() = PlayerScore(
            place = 1,
            playerName = "Olenkka",
            playerAvatar = UiPlayerAvatarType.Fluffy,
            score = 20,
        )

        fun secondPlace() = PlayerScore(
            place = 2,
            playerName = "Katyushka",
            playerAvatar = UiPlayerAvatarType.Scout,
            score = 19,
        )

        fun thirdPlace() = PlayerScore(
            place = 3,
            playerName = "Andrew",
            playerAvatar = null,
            score = 16,
        )

        fun forthPlace() = PlayerScore(
            place = 4,
            playerName = "Maryanko",
            playerAvatar = UiPlayerAvatarType.Goober,
            score = -3,
        )
    }

    override val values: Sequence<PlayerScore> = sequenceOf(
        firstPlace(),
        secondPlace(),
        thirdPlace(),
        forthPlace(),
    )
}
