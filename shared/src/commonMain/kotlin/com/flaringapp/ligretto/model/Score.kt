package com.flaringapp.ligretto.model

import kotlin.jvm.JvmInline

@JvmInline
value class Score(val value: Int) : Comparable<Int> by value {

    companion object {
        val Zero: Score = Score(0)
    }

    operator fun plus(other: Score) = Score(value + other.value)

    operator fun minus(other: Score) = Score(value - other.value)
}
