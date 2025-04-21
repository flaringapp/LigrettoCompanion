package com.flaringapp.ligretto.core.util.common

sealed class ValueOrError<out Value, out Error> {

    data class Value<out Value>(
        val value: Value,
    ) : ValueOrError<Value, Nothing>()

    data class Error<out Error>(
        val error: Error,
    ) : ValueOrError<Nothing, Error>()
}

inline fun <Value, Error> ValueOrError<Value, Error>.valueOrElse(block: (Error) -> Value): Value {
    return when (this) {
        is ValueOrError.Value<Value> -> this.value
        is ValueOrError.Error<Error> -> block(error)
    }
}
