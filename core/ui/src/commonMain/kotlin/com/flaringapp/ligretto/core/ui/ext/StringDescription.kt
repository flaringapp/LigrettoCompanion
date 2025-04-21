package com.flaringapp.ligretto.core.ui.ext

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Immutable
interface StringDescription {

    @Composable
    fun resolve(): String
}

object StringDescriptionEmpty : StringDescription {

    @Composable
    override fun resolve(): String = ""
}

data class StringDescriptionRaw(
    val text: String,
) : StringDescription {

    @Composable
    override fun resolve(): String = text
}

data class StringDescriptionRes(
    val resource: StringResource,
) : StringDescription {

    @Composable
    override fun resolve(): String = stringResource(resource)
}

data class StringDescriptionResWithArgs(
    val resource: StringResource,
    val args: List<Any>,
) : StringDescription {

    @Composable
    override fun resolve(): String = stringResource(resource, *args.toTypedArray())
}

fun StringResource.asDescription(): StringDescription = StringDescriptionRes(this)

fun String.asDescription(): StringDescription = StringDescriptionRaw(this)
