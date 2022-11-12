package com.ftp.keberlanjutanumkmbsc.utils

import android.view.View
import android.widget.*
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged

fun String?.isRequiredField(): Boolean {
    return this != null && isNotEmpty() && isNotBlank()
}

fun setButtonEnabledDisabledState(
    enabled: Boolean,
    button: TextView,
    buttonDisabled: TextView
) {
    button.isVisible = enabled
    buttonDisabled.isVisible = !enabled
}

fun setEditTextAndButtonListener(
    listOfEditText: List<EditText>,
    button: TextView,
    buttonDisabled: TextView
) {
    listOfEditText.forEach {
        it.doAfterTextChanged {
            listOfEditText.isEditTextFilled { isFilled ->
                button.isVisible = isFilled
                buttonDisabled.isVisible = !isFilled
            }
        }
    }
}

fun isEditTextsNotEmpty(
    listOfEditText: List<EditText>,
): Boolean {
    var state = true
    listOfEditText.forEach {
        state = state && it.text.isNotEmpty()
    }
    return state
}

fun setEditTextAndSpinnerAndButtonListener(
    listOfEditText: List<EditText>,
    listOfSpinner: List<Spinner>,
    button: TextView,
    buttonDisabled: TextView
) {
    listOfEditText.forEach {
        it.doAfterTextChanged {
            checkEditTextAndSpinnerAndButton(listOfEditText, listOfSpinner, button, buttonDisabled)
        }
    }
    listOfSpinner.forEach {
        it.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                checkEditTextAndSpinnerAndButton(
                    listOfEditText,
                    listOfSpinner,
                    button,
                    buttonDisabled
                )
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

        }
    }
}

private fun checkEditTextAndSpinnerAndButton(
    listOfEditText: List<EditText>,
    listOfSpinner: List<Spinner>,
    button: TextView,
    buttonDisabled: TextView
) {
    listOfEditText.isEditTextFilled { isFilled ->
        val listOfSpinnerFilled = listOfSpinner.map { spinner -> spinner.selectedItemPosition != 0 }
        var isSpinnerFilled = true
        listOfSpinnerFilled.forEach { spinnerFilled ->
            isSpinnerFilled = isSpinnerFilled && spinnerFilled
        }
        button.isVisible = isFilled && isSpinnerFilled
        buttonDisabled.isVisible = !isFilled || !isSpinnerFilled
    }
}

fun setEditTextAndButtonAndCheckBoxListener(
    listOfEditText: List<EditText>,
    checkBox: CheckBox,
    button: TextView,
    buttonDisabled: TextView
) {
    listOfEditText.forEach {
        it.doAfterTextChanged {
            listOfEditText.isEditTextFilled { isFilled ->
                button.isVisible = isFilled && checkBox.isChecked
                buttonDisabled.isVisible = !isFilled || !checkBox.isChecked
            }
        }
        checkBox.setOnCheckedChangeListener { _, _ ->
            listOfEditText.isEditTextFilled { isFilled ->
                button.isVisible = isFilled && checkBox.isChecked
                buttonDisabled.isVisible = !isFilled || !checkBox.isChecked
            }
        }
    }
}