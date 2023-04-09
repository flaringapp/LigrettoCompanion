package com.flaringapp.ligretto.model

import kotlin.jvm.JvmInline

@JvmInline
value class Score(val value: Int) : Comparable<Score> {

    companion object {
        val Zero: Score = Score(0)
    }

    operator fun plus(other: Score) = Score(value + other.value)

    operator fun minus(other: Score) = Score(value - other.value)

    override fun compareTo(other: Score): Int {
        return value.compareTo(other.value)
    }
}
