package com.ftp.keberlanjutanumkmbsc.data.pref

import androidx.annotation.Keep
import com.chibatching.kotpref.KotprefModel

@Keep
object ProfilePrefs : KotprefModel() {

    var first_time by booleanPref(true)
    var idFirebase by stringPref("")
    var role by stringPref("")

    var idUser by stringPref("")
    var email by stringPref("")
    var fullname by stringPref("")
    var jenisKelamin by stringPref("")
    var pekerjaan by stringPref("")
    var namaUsaha by stringPref("")
    var alamat by stringPref("")
    var noHP by stringPref("")
    var tahunPengalaman by stringPref("")
    var jenisProdukYangDijual by stringPref("")

}