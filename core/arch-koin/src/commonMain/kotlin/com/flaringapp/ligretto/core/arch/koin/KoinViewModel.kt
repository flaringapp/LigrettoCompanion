package com.flaringapp.ligretto.core.arch.koin

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import org.koin.compose.currentKoinScope
import org.koin.compose.stable.rememberStableParametersDefinition
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.scope.Scope

@Composable
inline fun <reified T : ViewModel> koinViewModel(
    scope: Scope = currentKoinScope(),
    noinline parameters: ParametersDefinition? = null,
): T {
    val st = parameters?.let { rememberStableParametersDefinition(parameters) }
    return viewModel { scope.get(null, st?.parametersDefinition) }
}
