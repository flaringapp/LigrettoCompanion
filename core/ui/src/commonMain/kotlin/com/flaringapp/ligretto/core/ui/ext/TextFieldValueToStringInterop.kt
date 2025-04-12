package com.flaringapp.ligretto.core.ui.ext

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisallowComposableCalls
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue

typealias TextFieldValueCustomization =
    @DisallowComposableCalls (oldValue: TextFieldValue, newValue: String) -> TextFieldValue

typealias TextFieldValueOnChangeCustomization =
    @DisallowComposableCalls (oldValue: TextFieldValue, newValue: TextFieldValue) -> TextFieldValue

object TextFieldValueToStringInteropDefaults {

    val cursorToEndOnValueChange: TextFieldValueCustomization = { oldValue, newValue ->
        val selection = if (newValue != oldValue.text) {
            TextRange(newValue.length)
        } else {
            oldValue.selection
        }
        oldValue.copy(
            text = newValue,
            selection = selection,
        )
    }
}

/**
 * A holder for TextField [value] and [onValueChange] with [TextFieldValue] type to be set
 * when using [rememberTextFieldValueToStringInterop] and co.
 */
interface TextFieldValueToStringInterop {
    val value: TextFieldValue
    val onValueChange: (TextFieldValue) -> Unit
}

class TextFieldValueToStringInteropImpl : TextFieldValueToStringInterop {
    override lateinit var value: TextFieldValue
    override lateinit var onValueChange: (TextFieldValue) -> Unit
}

/**
 * A default (common) usage of [rememberTextFieldValueToStringInterop]. What it does is:
 * - sets a cursor to the end when the [value] is changed from the outside;
 * - allows to customize (e.g. filer) a value that comes from the inside (text field onValueChange)
 * using [customizeValueOnChange], so that [TextFieldValue] state is not updated twice.
 *
 * Example usage:
 * ```
 * val interop = rememberDefaultTextFieldValueToStringInterop(
 *     value = value,
 *     onValueChange = onValueChange,
 *     customizeValueOnChange = { newValue ->
 *         newValue.filter { it.isDigit() }
 *     },
 * )
 *
 * TextField(
 *     value = interop.value,
 *     onValueChange interop.onValueChange,
 *     ...
 * )
 * ```
 *
 * @param value the input [String] text to be shown in the text field
 * @param onValueChange the callback that is triggered when the input service updates the text
 * @param customizeValueOnChange the function to customize [String] value coming from the inner
 * text field onValueChange callback before it updates the inner state
 * @see TextFieldValueToStringInteropDefaults.cursorToEndOnValueChange
 */
@Composable
inline fun rememberDefaultTextFieldValueToStringInterop(
    value: String,
    crossinline onValueChange: (String) -> Unit,
    crossinline customizeValueOnChange: @DisallowComposableCalls (String) -> String = { it },
): TextFieldValueToStringInterop {
    return rememberTextFieldValueToStringInterop(
        value = value,
        onValueChange = onValueChange,
        customizeTextFieldValue = TextFieldValueToStringInteropDefaults.cursorToEndOnValueChange,
        customizeTextFieldValueOnChange = block@{ oldValue, newValue ->
            val customValue = customizeValueOnChange(newValue.text)
            if (customValue == newValue.text) return@block newValue
            if (customValue == oldValue.text) return@block oldValue

            newValue.copy(text = customValue)
        },
    )
}

/**
 * Copies logic from [androidx.compose.foundation.text.BasicTextField] with string value/callback
 * (including the explanatory comments), and adds some flexibility to customize [TextFieldValue]:
 * - using [customizeTextFieldValue] when [value] is changed from the outside;
 * - using [customizeTextFieldValueOnChange] new value is received from the inside (from text
 * input service).
 *
 * @param value the input [String] text to be shown in the text field
 * @param onValueChange the callback that is triggered when the input service updates the text
 * @param customizeTextFieldValue the function to update [TextFieldValue] with a new value passed
 * via [value]. Defaults to [androidx.compose.foundation.text.BasicTextField] behavior: just
 * sets a new value
 * @param customizeTextFieldValueOnChange the function to update (e.g. filter) a [TextFieldValue]
 * that comes from the inner onValueChange before it is set to the inner state. Defaults to no
 * modification
 * @return [TextFieldValueToStringInterop] the value and callback holder of [TextFieldValue] type
 * to be passed to the target TextField
 * @see TextFieldValueToStringInterop
 * @see rememberDefaultTextFieldValueToStringInterop
 */
@Composable
inline fun rememberTextFieldValueToStringInterop(
    value: String,
    crossinline onValueChange: (String) -> Unit,
    crossinline customizeTextFieldValue: TextFieldValueCustomization =
        { oldValue, newValue -> oldValue.copy(text = newValue) },
    crossinline customizeTextFieldValueOnChange: TextFieldValueOnChangeCustomization =
        { _, newValue -> newValue },
): TextFieldValueToStringInterop {
    // Holds the latest internal TextFieldValue state. We need to keep it to have the correct value
    // of the composition.
    // Also customize the initial value
    var textFieldValueState by remember {
        val initialValue = customizeTextFieldValue(TextFieldValue(), value)
        mutableStateOf(initialValue)
    }

    // Holds the latest TextFieldValue that BasicTextField was recomposed with. We couldn't simply
    // pass `TextFieldValue(text = value)` to the CoreTextField because we need to preserve the
    // composition.
    val textFieldValue = customizeTextFieldValue(
        // old value
        textFieldValueState,
        // new value
        value,
    )

    SideEffect {
        if (textFieldValue.selection != textFieldValueState.selection ||
            textFieldValue.composition != textFieldValueState.composition
        ) {
            textFieldValueState = textFieldValue
        }
    }

    // Last String value that either text field was recomposed with or updated in the onValueChange
    // callback. We keep track of it to prevent calling onValueChange(String) for same String when
    // CoreTextField's onValueChange is called multiple times without recomposition in between.
    var lastTextValue by remember(value) { mutableStateOf(value) }

    // Original (copied) behavior with initial TextFieldValue customization
    val onTextFieldValueChange: (TextFieldValue) -> Unit = { newRawTextFieldValue ->
        val newTextFieldValue = customizeTextFieldValueOnChange(
            // old value
            textFieldValueState,
            // new value
            newRawTextFieldValue,
        )
        // Original block below
        textFieldValueState = newTextFieldValue

        val stringChangedSinceLastInvocation = lastTextValue != newTextFieldValue.text
        lastTextValue = newTextFieldValue.text

        if (stringChangedSinceLastInvocation) {
            onValueChange(newTextFieldValue.text)
        }
    }

    return remember { TextFieldValueToStringInteropImpl() }.apply {
        this.value = textFieldValue
        this.onValueChange = onTextFieldValueChange
    }
}
