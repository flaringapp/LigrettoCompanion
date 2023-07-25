package com.flaringapp.ligretto.feature.game.data.storage

import com.flaringapp.ligretto.core.database.LapPlayer as DatabaseLapPlayer
import com.flaringapp.ligretto.core.database.Player as DatabasePlayer
import com.flaringapp.ligretto.core.database.SelectAllByGameId as DatabaseGamePlayer
import com.flaringapp.ligretto.core.database.SelectAllByGameIdNumberAscending as DatabaseLap

internal data class GameDataStorageDto(
    val gamePlayers: List<DatabaseGamePlayer>,
    val laps: List<DatabaseLap>,
    val lapsPlayers: List<DatabaseLapPlayer>,
    val players: List<DatabasePlayer>,
)
