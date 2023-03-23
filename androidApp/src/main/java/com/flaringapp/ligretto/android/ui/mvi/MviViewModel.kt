package com.flaringapp.ligretto.android.ui.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class MviViewModel<State : UiState, Intent : UiIntent, Effect : UiEffect>(
    emptyState: State,
) : ViewModel(), Store<State, Intent, Effect>, Reducer<State, Intent> {

    protected val state
        get() = stateFlow.value

    private val stateFlow = MutableStateFlow(emptyState)

    protected val effectChannel: Channel<Effect> = Channel()

    private val intentFlow: MutableSharedFlow<Intent> = MutableSharedFlow()

    init {
        observeIntents()
    }

    override fun observeState(): StateFlow<State> {
        return stateFlow.asStateFlow()
    }

    override fun observeEffect(): Flow<Effect> {
        return effectChannel.receiveAsFlow()
    }

    override fun dispatch(intent: Intent) {
        viewModelScope.launch {
            intentFlow.emit(intent)
        }
    }

    protected inline fun updateState(build: State.() -> State): State {
        return state.build()
    }

    protected inline fun setEffect(build: () -> Effect) {
        val effect = build()
        viewModelScope.launch { effectChannel.send(effect) }
    }

    private fun observeIntents() {
        viewModelScope.launch {
            intentFlow.collect { intent ->
                val newState = reduce(state, intent)
                stateFlow.value = newState
            }
        }
    }
}
