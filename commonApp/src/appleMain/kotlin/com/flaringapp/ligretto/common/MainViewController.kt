package com.flaringapp.ligretto.common

import androidx.compose.ui.uikit.OnFocusBehavior
import androidx.compose.ui.window.ComposeUIViewController
import com.flaringapp.ligretto.common.ui.AppContent

@Suppress("FunctionName", "unused", "ktlint:standard:function-naming")
fun MainViewController() = ComposeUIViewController(
    configure = {
        onFocusBehavior = OnFocusBehavior.DoNothing
    },
) {
    AppContent()
}
