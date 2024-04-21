@file:OptIn(ExperimentalResourceApi::class)

package com.flaringapp.ligretto.core.ui

import ligretto_companion.core.ui_mp.generated.resources.cancel
import ligretto_companion.core.ui_mp.generated.resources.ok
import ligretto_companion.core.ui_mp.generated.resources.yes
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.StringResource
import ligretto_companion.core.ui_mp.generated.resources.Res as GenRes

// Generated code is internal, we need to explicitly expose resources
object Res {

    @Suppress("ClassName")
    object string {

        val cancel: StringResource
            get() = GenRes.string.cancel

        val ok: StringResource
            get() = GenRes.string.ok

        val yes: StringResource
            get() = GenRes.string.yes
    }
}
