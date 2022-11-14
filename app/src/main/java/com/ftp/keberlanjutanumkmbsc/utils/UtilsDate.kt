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

    var indonesianDays =
        arrayOf("Sabtu", "Minggu", "Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu")

    fun getDayNameDateMonthYearTs(parTs: Long): String {
        val date = Date(parTs)
        val calender = Calendar.getInstance()
        calender.time = date
        val year = calender.get(Calendar.YEAR)
        val month = calender.get(Calendar.MONTH)
        val day = calender.get(Calendar.DAY_OF_MONTH)
        val dayOfWeek = calender.get(Calendar.DAY_OF_WEEK)
        val dayName = indonesianDays[dayOfWeek]
        var strMonth = ""
        when (month + 1) {
            1 -> strMonth = "Januari"
            2 -> strMonth = "Februari"
            3 -> strMonth = "Maret"
            4 -> strMonth = "April"
            5 -> strMonth = "Mei"
            6 -> strMonth = "Juni"
            7 -> strMonth = "Juli"
            8 -> strMonth = "Agustus"
            9 -> strMonth = "September"
            10 -> strMonth = "Oktober"
            11 -> strMonth = "November"
            12 -> strMonth = "Desember"
        }
        return dayName + ", " + day.toString().plus(" ").plus(strMonth).plus(" ")
            .plus(year.toString())
    }

    fun getCurrentDaysDateMonthYear(): String {
        val currentDate = getCurrentDateTime()
        return getDayNameDateMonthYearTs(currentDate.time)
    }

    fun getDaysDateMonthYear(isoString: String? = null): String {
        val isoDate = isoString?.ISOTimeToDateTime()
        val currentDate = isoDate ?: getCurrentDateTime()
        return getDayNameDateMonthYearTs(currentDate.time)
    }

    fun String.ISOTimeToDateTime(usingTimeZone: Boolean = true): Date? {
        val df = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        if (usingTimeZone)
            df.timeZone = TimeZone.getTimeZone("UTC")
        return df.parse(this)
    }
}