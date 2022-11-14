package com.ftp.keberlanjutanumkmbsc.utils

import android.content.Intent
import com.ftp.keberlanjutanumkmbsc.data.logic.Indikator
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

fun getGsonInstance(): Gson {
    return GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create()
}

fun Any.getGSONString(): String {
    return try {
        getGsonInstance().toJson(this)
    } catch (e: Exception) {
        ""
    }
}

const val LIST_INDIKATOR = "LIST_INDIKATOR"
const val LIST_STRING = "LIST_STRING"

fun Intent.setListIndikator(perangkat: MutableList<Indikator>) {
    putExtra(LIST_INDIKATOR, perangkat.getGSONString())
}

fun Intent.getListIndikator(): MutableList<Indikator> {
    return if (hasExtra(LIST_INDIKATOR)) {
        val dataAsString = getStringExtra(LIST_INDIKATOR) ?: ""
        try {
            val typeToken = object : TypeToken<MutableList<Indikator>>() {}.type
            Gson().fromJson(dataAsString, typeToken)
        } catch (e: Exception) {
            mutableListOf()
        }
    } else {
        mutableListOf()
    }
}
fun Intent.setListString(perangkat: List<String>) {
    putExtra(LIST_STRING, perangkat.getGSONString())
}

fun Intent.getListString(): MutableList<String> {
    return if (hasExtra(LIST_STRING)) {
        val dataAsString = getStringExtra(LIST_STRING) ?: ""
        try {
            val typeToken = object : TypeToken<MutableList<String>>() {}.type
            Gson().fromJson(dataAsString, typeToken)
        } catch (e: Exception) {
            mutableListOf()
        }
    } else {
        mutableListOf()
    }
}