package com.ftp.keberlanjutanumkmfahp.utils

object Utils {
    fun getRandomString(length: Int) : String {
        val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }


    fun convertToString(list : List<Boolean>): String {
        var listString = ""
        for (data in list){
            listString = "$listString$data,"
        }
        return listString
    }

}