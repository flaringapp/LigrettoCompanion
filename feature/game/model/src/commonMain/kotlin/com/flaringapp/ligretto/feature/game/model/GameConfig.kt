package com.flaringapp.ligretto.feature.game.model

import com.flaringapp.ligretto.feature.player.model.Player
import kotlin.time.Duration

data class GameConfig(
    val players: List<Player>,
    val targetScore: Score? = null,
    val timeLimit: Duration? = null,
)
