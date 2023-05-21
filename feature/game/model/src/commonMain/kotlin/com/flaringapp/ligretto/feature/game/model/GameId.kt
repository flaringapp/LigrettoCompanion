package com.flaringapp.ligretto.feature.game.model

import kotlin.jvm.JvmInline

@JvmInline
value class GameId(val value: Int) {

    companion object {
        fun zero() = GameId(0)
    }
}
