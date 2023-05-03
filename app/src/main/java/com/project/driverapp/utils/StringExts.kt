package com.project.driverapp.utils

fun String.toEmail(): String {
    return "$this@gmail.com"
}

fun String.addTwoDot(): String {
    return ": $this"
}

fun Double?.toTwoNumberBehindComma(): String {
    return String.format("%.2f", this)
}

