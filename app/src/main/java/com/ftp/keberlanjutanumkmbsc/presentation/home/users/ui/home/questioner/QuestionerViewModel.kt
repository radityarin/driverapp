package com.ftp.keberlanjutanumkmbsc.presentation.home.users.ui.home.questioner

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ftp.keberlanjutanumkmbsc.data.logic.BalanceScoreCard
import com.ftp.keberlanjutanumkmbsc.data.logic.Indikator
import com.ftp.keberlanjutanumkmbsc.data.logic.Kuesioner
import com.ftp.keberlanjutanumkmbsc.data.logic.Perspektif
import com.ftp.keberlanjutanumkmbsc.data.pref.ProfilePrefs
import com.ftp.keberlanjutanumkmbsc.data.utils.Resource
import com.ftp.keberlanjutanumkmbsc.domain.usecases.questioner.QuestionerUseCase
import com.ftp.keberlanjutanumkmbsc.presentation.base.BaseViewModel
import com.ftp.keberlanjutanumkmbsc.utils.Utils.getRandomString
import com.ftp.keberlanjutanumkmbsc.utils.UtilsDate
import kotlinx.coroutines.launch

class QuestionerViewModel(
    private val questionerUseCase: QuestionerUseCase
) : BaseViewModel() {

    var listIndikator = mutableListOf<Indikator>()
    var listIndikatorKeuangan = MutableLiveData<MutableList<Indikator>>()
    var listIndikatorKepuasan = MutableLiveData<MutableList<Indikator>>()
    var listIndikatorPembelajaran = MutableLiveData<MutableList<Indikator>>()
    var listIndikatorProsesBisnis = MutableLiveData<MutableList<Indikator>>()
    var listIndikatorSosial = MutableLiveData<MutableList<Indikator>>()
    var listIndikatorLingkungan = MutableLiveData<MutableList<Indikator>>()
    private var balanceScoreCard: BalanceScoreCard = BalanceScoreCard()

    var scoreLiveData = MutableLiveData<Double>()
    var scoreCategoryLiveData = MutableLiveData<String>()
    var listStrategiKeuangan = MutableLiveData<List<String>>()
    var listStrategiKepuasan = MutableLiveData<List<String>>()
    var listStrategiPembelajaran= MutableLiveData<List<String>>()
    var listStrategiProsesBisnis = MutableLiveData<List<String>>()
    var listStrategiSosial = MutableLiveData<List<String>>()
    var listStrategiLingkungan = MutableLiveData<List<String>>()

    private fun setData(tipePerspektif: String, listInput: List<String>): MutableList<Indikator> {
        return when (tipePerspektif) {
            Perspektif.KEUANGAN -> {
                balanceScoreCard.insertPerspektifKeuangan(
                    totalPendapatan = listInput[0].toDouble(),
                    totalBiaya = listInput[1].toDouble(),
                    volumeProduksiSekarang = listInput[2].toDouble(),
                    volumeProduksiSebelumnya = listInput[3].toDouble(),
                    penjualanSekarang = listInput[4].toDouble(),
                    penjualanSebelumnya = listInput[5].toDouble(),
                )
            }
            Perspektif.KEPUASAN_PELANGGAN -> {
                balanceScoreCard.insertPerspektifKepuasanPelanggan(
                    totalKonsumenTerlayani = listInput[6].toDouble(),
                    totalKeluhanKonsumen = listInput[7].toDouble(),
                    totalKonsumenLama = listInput[8].toDouble(),
                    totalKonsumenTigaBulanTerakhir = listInput[9].toDouble()
                )
            }
            Perspektif.PEMBELAJARAN_DAN_PERTUMBUHAN -> {
                balanceScoreCard.insertPerspektifPembelajaranDanPertumbuhan(
                    totalKaryawanYangMengikutiPelatihan = listInput[10].toDouble(),
                    totalKaryawan = listInput[11].toDouble(),
                    totalKeluhanTerlayani = listInput[12].toDouble(),
                    totalKeluhanKaryawan = listInput[13].toDouble(),
                    hasilKerjaKaryawan = listInput[14].toDouble(),
                    jamKerja = listInput[15].toDouble()
                )
            }
            Perspektif.PROSES_BISNIS_INTERNAL -> {
                balanceScoreCard.insertPerspektifProsesBisnisInternal(
                    totalProdukYangBaik = listInput[6].toDouble(),
                    totalProduk = listInput[17].toDouble(),
                    totalProgramTerlaksana = listInput[18].toDouble(),
                    totalRencanaProgram = listInput[19].toDouble()
                )
            }
            Perspektif.SOSIAL -> {
                balanceScoreCard.insertPerspektifSosial(
                    jumlahKaryawanMasyarakatSekitar = listInput[20].toDouble(),
                    jumlahKaryawan = listInput[21].toDouble()
                )
            }
            Perspektif.LINGKUNGAN -> {
                balanceScoreCard.insertPerspektifLingkungan(
                    produkDaurUlang = listInput[22].toDouble(),
                    jumlahProduk = listInput[23].toDouble(),
                    jumlahLimbarDaurUlang = listInput[24].toDouble(),
                    totalLimbahYangDihasilkan = listInput[25].toDouble()
                )
            }
            else -> {
                mutableListOf()
            }
        }
    }

    fun setInputData(listString: MutableList<String>) {
        val indikatorKeuangan = setData(Perspektif.KEUANGAN, listString)
        val indikatorKepuasan = setData(Perspektif.KEPUASAN_PELANGGAN, listString)
        val indikatorPembelajaran = setData(Perspektif.PEMBELAJARAN_DAN_PERTUMBUHAN, listString)
        val indikatorProsesBisnis = setData(Perspektif.PROSES_BISNIS_INTERNAL, listString)
        val indikatorSosial = setData(Perspektif.SOSIAL, listString)
        val indikatorLingkungan = setData(Perspektif.LINGKUNGAN, listString)

        listIndikatorKeuangan.value = indikatorKeuangan
        listIndikatorKepuasan.value = indikatorKepuasan
        listIndikatorPembelajaran.value = indikatorPembelajaran
        listIndikatorProsesBisnis.value = indikatorProsesBisnis
        listIndikatorSosial.value = indikatorSosial
        listIndikatorLingkungan.value = indikatorLingkungan

        listIndikator.addAll(listIndikatorKeuangan.value ?: emptyList())
        listIndikator.addAll(listIndikatorKepuasan.value ?: emptyList())
        listIndikator.addAll(listIndikatorPembelajaran.value ?: emptyList())
        listIndikator.addAll(listIndikatorProsesBisnis.value ?: emptyList())
        listIndikator.addAll(listIndikatorSosial.value ?: emptyList())
        listIndikator.addAll(listIndikatorLingkungan.value ?: emptyList())

        var hasil = 0.0
        hasil += listIndikator.sumOf { it.hasil }
        val skorKategori = balanceScoreCard.getKategoriSustainibility(hasil)
        scoreLiveData.value = hasil
        scoreCategoryLiveData.value = skorKategori

        listStrategiKeuangan.value = balanceScoreCard.getStrategi(Perspektif.KEUANGAN, skorKategori)
        listStrategiKepuasan.value = balanceScoreCard.getStrategi(Perspektif.KEPUASAN_PELANGGAN, skorKategori)
        listStrategiPembelajaran.value = balanceScoreCard.getStrategi(Perspektif.PEMBELAJARAN_DAN_PERTUMBUHAN, skorKategori)
        listStrategiProsesBisnis.value = balanceScoreCard.getStrategi(Perspektif.PROSES_BISNIS_INTERNAL, skorKategori)
        listStrategiSosial.value = balanceScoreCard.getStrategi(Perspektif.SOSIAL, skorKategori)
        listStrategiLingkungan.value = balanceScoreCard.getStrategi(Perspektif.LINGKUNGAN, skorKategori)

        val kuesioner = Kuesioner(
            idKuesioner = getRandomString(20),
            idUser = ProfilePrefs.idFirebase,
            tanggal = UtilsDate.getCurrentDaysDateMonthYear(),
            indikatorKeuangan = indikatorKeuangan.map { it.hasil },
            indikatorKepuasan = indikatorKepuasan.map { it.hasil },
            indikatorPembelajaran = indikatorPembelajaran.map { it.hasil },
            indikatorProsesBisnis = indikatorProsesBisnis.map { it.hasil },
            indikatorSosial = indikatorSosial.map { it.hasil },
            indikatorLingkungan = indikatorLingkungan.map { it.hasil },
            skor = hasil,
            skorKategori = skorKategori,
            strategiKeuangan = balanceScoreCard.getStrategi(Perspektif.KEUANGAN, skorKategori),
            strategiKepuasan = balanceScoreCard.getStrategi(Perspektif.KEPUASAN_PELANGGAN, skorKategori),
            strategiPembelajaran = balanceScoreCard.getStrategi(Perspektif.PEMBELAJARAN_DAN_PERTUMBUHAN, skorKategori),
            strategiProsesBisnis = balanceScoreCard.getStrategi(Perspektif.KEUANGAN, skorKategori),
            strategiSosial = balanceScoreCard.getStrategi(Perspektif.KEUANGAN, skorKategori),
            strategiLingkungan = balanceScoreCard.getStrategi(Perspektif.KEUANGAN, skorKategori),
        )

        viewModelScope.launch {
            questionerUseCase.addQuestioner(kuesioner).collect {
                when (it) {
                    is Resource.Loading -> {
                        showLoadingLiveData.value = true
                    }
                    is Resource.Success -> {
                        showLoadingLiveData.value = false
                    }
                    is Resource.Error -> {
                        showLoadingLiveData.value = false
                        println("ERROR " + it.message)
                    }
                }
            }
        }
    }


}
