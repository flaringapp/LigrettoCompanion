package com.flaringapp.ligretto.core.arch

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface UiState

interface UiIntent

interface UiEffect

interface Store<State : UiState, Intent : UiIntent, Effect : UiEffect> {

    fun observeState(): StateFlow<State>

    fun observeEffect(): Flow<Effect>

    fun dispatch(intent: Intent)
}

interface Reducer<S : UiState, I : UiIntent> {

    fun reduce(state: S, intent: I): S
}

inline fun <Intent : UiIntent> Store<*, Intent, *>.dispatch(build: () -> Intent) {
    dispatch(build())
}
