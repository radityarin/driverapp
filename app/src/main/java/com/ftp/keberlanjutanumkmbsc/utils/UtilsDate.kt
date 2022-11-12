package com.ftp.keberlanjutanumkmbsc.utils

import java.text.SimpleDateFormat
import java.util.*


object UtilsDate {
    const val ONE_MINUTE = 60 * 1000.toLong()
    const val ONE_HOUR = 60 * ONE_MINUTE

    fun Date.toStringDate(
        format: String = "yyyy/MM/dd HH:mm:ss",
        locale: Locale = Locale.getDefault()
    ): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }

    fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }

    fun getCurrentDateTimeISO(): String {
        val df = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
        return df.format(Date())
    }

    fun getCurrentDateTimeForUsersSide(): String {
        val df = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
        return df.format(Date())
    }

}