package com.flaringapp.ligretto.feature.home.domain.model

import com.flaringapp.ligretto.feature.game.model.GameSnapshot

data class HomeData(
    val activeGame: GameSnapshot?,
    val previousGame: GameSnapshot?,
)
