package com.flaringapp.ligretto.feature.game.ui.common

import ligretto_companion.feature.game.ui.generated.resources.Res
import ligretto_companion.feature.game.ui.generated.resources.ic_first_place
import ligretto_companion.feature.game.ui.generated.resources.ic_second_place
import ligretto_companion.feature.game.ui.generated.resources.ic_third_place
import org.jetbrains.compose.resources.DrawableResource

internal object GamePlayerPlaceIcon {

    fun resolve(place: Int): DrawableResource? {
        return when (place) {
            1 -> Res.drawable.ic_first_place
            2 -> Res.drawable.ic_second_place
            3 -> Res.drawable.ic_third_place
            else -> null
        }
    }
}
