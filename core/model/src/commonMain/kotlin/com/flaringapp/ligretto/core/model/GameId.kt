package com.flaringapp.ligretto.core.model

import kotlin.jvm.JvmInline

@JvmInline
value class GameId(val value: Int) {

    companion object {
        fun zero() = GameId(0)
    }
}
