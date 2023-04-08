package com.flaringapp.ligretto.model

import kotlin.time.Duration

data class GameConfig(
    val players: List<Player>,
    val targetScore: Score? = null,
    val timeLimit: Duration? = null,
)
