package com.flaringapp.ligretto.feature.game.domain.model

import com.flaringapp.ligretto.feature.game.model.Player
import com.flaringapp.ligretto.feature.game.model.Score
import kotlin.time.Duration

data class GameConfig(
    val players: List<Player>,
    val targetScore: Score? = null,
    val timeLimit: Duration? = null,
)
