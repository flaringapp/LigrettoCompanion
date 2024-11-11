package com.flaringapp.ligretto.feature.game.ui.end.screen

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.flaringapp.ligretto.core.ui.ext.asUiList
import com.flaringapp.ligretto.core.ui.ext.emptyUiList
import com.flaringapp.ligretto.feature.game.ui.end.GameEndState

internal class GameEndStateProvider : PreviewParameterProvider<GameEndState> {

    companion object {

        fun one() = GameEndState(
            winners = GameEndState.Winners(
                firstPlace = GameEndPlayerResultProvider.default(),
                secondPlace = null,
                thirdPlace = null,
                otherPlaces = emptyUiList(),
            ),
        )

        fun two() = with(one()) {
            copy(
                winners = winners?.copy(
                    secondPlace = GameEndPlayerResultProvider.default(),
                ),
            )
        }

        fun three() = with(two()) {
            copy(
                winners = winners?.copy(
                    thirdPlace = GameEndPlayerResultProvider.default(),
                ),
            )
        }

        fun aLot() = with(three()) {
            copy(
                winners = winners?.copy(
                    otherPlaces = List(5) {
                        GameEndPlayerResultProvider.default()
                    }.asUiList(),
                ),
            )
        }
    }

    override val values: Sequence<GameEndState> = sequenceOf(
        one(),
        two(),
        three(),
        aLot(),
    )
}
