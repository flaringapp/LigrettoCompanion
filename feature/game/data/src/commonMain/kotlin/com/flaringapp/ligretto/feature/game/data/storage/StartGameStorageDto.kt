package com.flaringapp.ligretto.feature.game.data.storage

internal data class StartGameStorageDto(
    val id: Long,
    val timeStarted: Long,
    val playerIds: List<Long>,
)
