package com.flaringapp.ligretto.core.domain.model

import com.flaringapp.ligretto.core.model.Player
import com.flaringapp.ligretto.core.model.Score
import kotlin.time.Duration

data class GameConfig(
    val players: List<Player>,
    val targetScore: Score? = null,
    val timeLimit: Duration? = null,
)
