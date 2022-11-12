package com.ftp.keberlanjutanumkmbsc.utils

import android.os.Handler
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged

const val TYPING_DELAY = 500L

fun EditText.onDone(callback: () -> Unit) {
    imeOptions = EditorInfo.IME_ACTION_DONE
    maxLines = 1
    setOnEditorActionListener { _, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            callback.invoke()
            true
        }
        false
    }
}

fun EditText.onAfterEditing(callback: () -> Unit) {
    doAfterTextChanged {
        Handler().postDelayed({
            callback.invoke()
        }, TYPING_DELAY)
    }
}

fun List<EditText>.onAfterEditing(callback: () -> Unit) {
    var check = false
    forEach {
        it.doAfterTextChanged {

        }
        check = check && it.text.isNotEmpty()
    }
    callback.invoke()
}

fun List<EditText>.isEditTextFilled(callback: (Boolean) -> Unit) {
    var isFilled = true
    forEach {
        isFilled = isFilled && it.text.toString().isNotEmpty()
    }
    callback.invoke(isFilled)
}

fun EditText.clear() {
    this.setText("")
}