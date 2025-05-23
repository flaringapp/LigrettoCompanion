package com.flaringapp.ligretto.feature.game.domain.contracts

import com.flaringapp.ligretto.feature.game.model.Game
import com.flaringapp.ligretto.feature.game.model.GameConfig
import com.flaringapp.ligretto.feature.game.model.GameId
import com.flaringapp.ligretto.feature.game.model.GameSnapshot
import com.flaringapp.ligretto.feature.game.model.Lap
import com.flaringapp.ligretto.feature.game.model.Player
import com.flaringapp.ligretto.feature.game.model.Score
import kotlin.time.Duration
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface GameRepository {

    val currentGameFlow: StateFlow<Game?>
    val currentLapFlow: StateFlow<Lap?>

    val previousGameFlow: Flow<GameSnapshot?>

    /**
     * @return Last [Game] collected from the [previousGameFlow]
     */
    fun getCachedPreviousGame(): GameSnapshot?

    fun getActiveGameId(): GameId?
    fun observeActiveGameId(): Flow<GameId?>

    suspend fun resumeGame(gameSnapshot: GameSnapshot)

    //region Game in progress
    suspend fun startGame(gameConfig: GameConfig): Game
    fun endGame(): Game?

    suspend fun changeGameSettings(
        targetScore: Score?,
        timeLimit: Duration?,
    ): Game

    suspend fun startNextLap(): Lap

    suspend fun updateLapPlayerCards(lap: Lap, player: Player)

    suspend fun endLap(gameWithLap: Game)
    //endregion
}
