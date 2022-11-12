package com.ftp.keberlanjutanumkmbsc.data.logic

class BalanceScoreCard {

    val kuesioner = Kuesioner()
    val perspektifKeuangan = Perspektif()
    val perspektifKepuasanPelanggan = Perspektif()
    val perspektifPembelajaranDanPertumbuhan = Perspektif()
    val perspektifProsesBisnisInternal = Perspektif()
    val perspektifSosial = Perspektif()
    val perspektifLingkungan = Perspektif()

    init {
        perspektifKeuangan.apply {
            namaPerspektif = Perspektif.KEUANGAN
            bobotPerspektif = 30.0
        }
        perspektifKepuasanPelanggan.apply {
            namaPerspektif = Perspektif.KEPUASAN_PELANGGAN
            bobotPerspektif = 20.0
        }
        perspektifPembelajaranDanPertumbuhan.apply {
            namaPerspektif = Perspektif.PEMBELAJARAN_DAN_PERTUMBUHAN
            bobotPerspektif = 10.0
        }
        perspektifProsesBisnisInternal.apply {
            namaPerspektif = Perspektif.PROSES_BISNIS_INTERNAL
            bobotPerspektif = 15.0
        }
        perspektifSosial.apply {
            namaPerspektif = Perspektif.SOSIAL
            bobotPerspektif = 12.5
        }
        perspektifLingkungan.apply {
            namaPerspektif = Perspektif.LINGKUNGAN
            bobotPerspektif = 12.5
        }
    }

    private fun getTingkatKinerja(actual: Double, standard: Double): Double {
        return when {
            actual > standard -> {
                90.0
            }
            actual == standard -> {
                60.0
            }
            actual < standard -> {
                40.0
            }
            else -> {
                40.0
            }
        }
    }

    private fun getKategoriSustainibility(score: Double): String {
        return when {
            score <= 40 -> {
                "Poor"
            }
            score > 40 && score <= 50 -> {
                "Marginal"
            }
            score > 50 && score <= 70 -> {
                "Average"
            }
            score > 70 && score <= 90 -> {
                "Good"
            }
            score > 90 && score <= 100 -> {
                "Excellent"
            }
            else -> {
                "Not found"
            }
        }
    }

    private fun calculateIndikator(
        calculation: Double,
        standardPerformance: Double,
        indikator: Indikator
    ): Indikator {
        val actualPerformance = calculation * 100.0
        val nilaiTingkatKinerja = getTingkatKinerja(actualPerformance, standardPerformance)
        val result =
            indikator.perspektif.bobotPerspektif * indikator.bobotIndikator * nilaiTingkatKinerja
        indikator.apply {
            kinerjaSebenarnya = actualPerformance
            kinerjaStandar = standardPerformance
            tingkatKinerja = nilaiTingkatKinerja
            hasil = result
            performansi = getKategoriSustainibility(result)
        }
        return indikator
    }

    /**
     * PERSPEKTIF KEUANGAN
     * 1. Keuntungan - 35%
     * 2. Pertumbuhan volume produksi - 30%
     * 3. Pertumbuhan Penjualan - 35%
     */

    fun calculateKeuanganKeuntungan(totalPendapatan: Int, totalBiaya: Int): Indikator {
        val perspektif = perspektifKeuangan
        val namaIndikator = "Keuangan"
        val bobotIndikator = 35.0
        val calculation = (totalPendapatan - totalBiaya).toDouble()
        val standardPerformance = 50.0
        val indikator = calculateIndikator(
            calculation = calculation,
            standardPerformance = standardPerformance,
            indikator = Indikator(
                perspektif = perspektif,
                namaIndikator = namaIndikator,
                bobotIndikator = bobotIndikator
            )
        )
        perspektifKeuangan.daftarIndikator.add(indikator)
        return indikator
    }

    fun calculateKeuanganPertumbuhanVolumeProduksi(
        volumeProduksiSekarang: Int,
        volumeProduksiSebelumnya: Int
    ): Indikator {
        val perspektif = perspektifKeuangan
        val namaIndikator = "Pertumbuhan volume produksi"
        val bobotIndikator = 30.0
        val calculation =
            (volumeProduksiSekarang - volumeProduksiSebelumnya) / volumeProduksiSebelumnya.toDouble()
        val standardPerformance = 10.0
        val indikator = calculateIndikator(
            calculation = calculation,
            standardPerformance = standardPerformance,
            indikator = Indikator(
                perspektif = perspektif,
                namaIndikator = namaIndikator,
                bobotIndikator = bobotIndikator
            )
        )
        perspektifKeuangan.daftarIndikator.add(indikator)
        return indikator
    }

    fun calculateKeuanganPertumbuhanPenjualan(
        penjualanSekarang: Int,
        penjualanSebelumnya: Int
    ): Indikator {
        val perspektif = perspektifKeuangan
        val namaIndikator = "Pertumbuhan penjualan"
        val bobotIndikator = 35.0
        val calculation = (penjualanSekarang - penjualanSebelumnya) / penjualanSebelumnya.toDouble()
        val standardPerformance = 15.0
        val indikator = calculateIndikator(
            calculation = calculation,
            standardPerformance = standardPerformance,
            indikator = Indikator(
                perspektif = perspektif,
                namaIndikator = namaIndikator,
                bobotIndikator = bobotIndikator
            )
        )
        perspektifKeuangan.daftarIndikator.add(indikator)
        return indikator
    }

    /**
     * PERSPEKTIF KEPUASAN PELANGGAN
     * 1. Keluhan pelanggan - 50%
     * 2. Customer retention - 50%
     */

    fun calculateKepuasanKeluhanPelanggan(
        totalKonsumenTerlayani: Int,
        totalKeluhanKonsumen: Int
    ): Indikator {
        val perspektif = perspektifKepuasanPelanggan
        val namaIndikator = "Keluhan pelanggan"
        val bobotIndikator = 50.0
        val calculation = totalKonsumenTerlayani / totalKeluhanKonsumen.toDouble()
        val standardPerformance = 75.0
        val indikator = calculateIndikator(
            calculation = calculation,
            standardPerformance = standardPerformance,
            indikator = Indikator(
                perspektif = perspektif,
                namaIndikator = namaIndikator,
                bobotIndikator = bobotIndikator
            )
        )
        perspektifKepuasanPelanggan.daftarIndikator.add(indikator)
        return indikator
    }

    fun calculateKepuasanCustomerRetention(
        totalKonsumenLama: Int,
        totalKonsumenTigaBulanTerakhir: Int
    ): Indikator {
        val perspektif = perspektifKepuasanPelanggan
        val namaIndikator = "Customer retention"
        val bobotIndikator = 50.0
        val calculation = totalKonsumenLama / totalKonsumenTigaBulanTerakhir.toDouble()
        val standardPerformance = 75.0
        val indikator = calculateIndikator(
            calculation = calculation,
            standardPerformance = standardPerformance,
            indikator = Indikator(
                perspektif = perspektif,
                namaIndikator = namaIndikator,
                bobotIndikator = bobotIndikator
            )
        )
        perspektifKepuasanPelanggan.daftarIndikator.add(indikator)
        return indikator
    }

    /**
     * PERSPEKTIF PEMBELAJARAN DAN PERTUMBUHAN
     * 1. Pelatihan karyawan - 35%
     * 2. Keluhan karyawan - 30%
     * 3. Produktivitas karyawan - 35%
     */

    fun calculatePembelajaranPelatihanKaryawan(
        totalKaryawanYangMengikutiPelatihan: Int,
        totalKaryawan: Int
    ): Indikator {
        val perspektif = perspektifPembelajaranDanPertumbuhan
        val namaIndikator = "Pelatihan karyawan"
        val bobotIndikator = 35.0
        val calculation = totalKaryawanYangMengikutiPelatihan / totalKaryawan.toDouble()
        val standardPerformance = 90.0
        val indikator = calculateIndikator(
            calculation = calculation,
            standardPerformance = standardPerformance,
            indikator = Indikator(
                perspektif = perspektif,
                namaIndikator = namaIndikator,
                bobotIndikator = bobotIndikator
            )
        )
        perspektifPembelajaranDanPertumbuhan.daftarIndikator.add(indikator)
        return indikator
    }

    fun calculatePembelajaranKeluhanKaryawan(
        totalKeluhanTerlayani: Int,
        totalKeluhanKaryawan: Int
    ): Indikator {
        val perspektif = perspektifPembelajaranDanPertumbuhan
        val namaIndikator = "Keluhan karyawan"
        val bobotIndikator = 30.0
        val calculation = totalKeluhanTerlayani / totalKeluhanKaryawan.toDouble()
        val standardPerformance = 50.0
        val indikator = calculateIndikator(
            calculation = calculation,
            standardPerformance = standardPerformance,
            indikator = Indikator(
                perspektif = perspektif,
                namaIndikator = namaIndikator,
                bobotIndikator = bobotIndikator
            )
        )
        perspektifPembelajaranDanPertumbuhan.daftarIndikator.add(indikator)
        return indikator
    }

    fun calculatePembelajaranProduktivitasKaryawan(
        hasilKerjaKaryawan: Int,
        jamKerja: Int
    ): Indikator {
        val perspektif = perspektifPembelajaranDanPertumbuhan
        val namaIndikator = "Produktivitas karyawan"
        val bobotIndikator = 35.0
        val calculation = hasilKerjaKaryawan / jamKerja.toDouble()
        val standardPerformance = 7.0
        val indikator = calculateIndikator(
            calculation = calculation,
            standardPerformance = standardPerformance,
            indikator = Indikator(
                perspektif = perspektif,
                namaIndikator = namaIndikator,
                bobotIndikator = bobotIndikator
            )
        )
        perspektifPembelajaranDanPertumbuhan.daftarIndikator.add(indikator)
        return indikator
    }

    /**
     * PERSPEKTIF PROSES BISNIS INTERNAL
     * 1. Kualitas produk - 50%
     * 2. Implementasi prosentase program - 50%
     */

    fun calculateBisnisKualitasProduk(totalProdukYangBaik: Int, totalProduk: Int): Indikator {
        val perspektif = perspektifProsesBisnisInternal
        val namaIndikator = "Kualitas produk"
        val bobotIndikator = 50.0
        val calculation = totalProdukYangBaik / totalProduk.toDouble()
        val standardPerformance = 85.0
        val indikator = calculateIndikator(
            calculation = calculation,
            standardPerformance = standardPerformance,
            indikator = Indikator(
                perspektif = perspektif,
                namaIndikator = namaIndikator,
                bobotIndikator = bobotIndikator
            )
        )
        perspektifProsesBisnisInternal.daftarIndikator.add(indikator)
        return indikator
    }

    fun calculateBisnisImplementasiProsentaseProgram(
        totalProgramTerlaksana: Int,
        totalRencanaProgram: Int
    ): Indikator {
        val perspektif = perspektifProsesBisnisInternal
        val namaIndikator = "Implementasi prosentase program"
        val bobotIndikator = 50.0
        val calculation = totalProgramTerlaksana / totalRencanaProgram.toDouble()
        val standardPerformance = 80.0
        val indikator = calculateIndikator(
            calculation = calculation,
            standardPerformance = standardPerformance,
            indikator = Indikator(
                perspektif = perspektif,
                namaIndikator = namaIndikator,
                bobotIndikator = bobotIndikator
            )
        )
        perspektifProsesBisnisInternal.daftarIndikator.add(indikator)
        return indikator
    }

    /**
     * PERSPEKTIF SOSIAL
     * 1. Peluang pekerjaan - 100%
     */

    fun calculateSosialPeluangPekerjaan(
        jumlahKaryawanMasyarakatSekitar: Int,
        jumlahKaryawan: Int
    ): Indikator {
        val perspektif = perspektifSosial
        val namaIndikator = "Peluang pekerjaan"
        val bobotIndikator = 100.0
        val calculation = jumlahKaryawanMasyarakatSekitar / jumlahKaryawan.toDouble()
        val standardPerformance = 80.0
        val indikator = calculateIndikator(
            calculation = calculation,
            standardPerformance = standardPerformance,
            indikator = Indikator(
                perspektif = perspektif,
                namaIndikator = namaIndikator,
                bobotIndikator = bobotIndikator
            )
        )
        perspektifSosial.daftarIndikator.add(indikator)
        return indikator
    }


    /**
     * PERSPEKTIF LINGKUNGAN
     * 1. Produk daur ulang - 50%
     * 2. Dekomposisi daur ulang - 50%
     */

    fun calculateLingkunganProdukDaurUlang(produkDaurUlang: Int, jumlahProduk: Int): Indikator {
        val perspektif = perspektifLingkungan
        val namaIndikator = "Produk daur ulang"
        val bobotIndikator = 50.0
        val calculation = produkDaurUlang / jumlahProduk.toDouble()
        val standardPerformance = 50.0
        val indikator = calculateIndikator(
            calculation = calculation,
            standardPerformance = standardPerformance,
            indikator = Indikator(
                perspektif = perspektif,
                namaIndikator = namaIndikator,
                bobotIndikator = bobotIndikator
            )
        )
        perspektifLingkungan.daftarIndikator.add(indikator)
        return indikator
    }

    fun calculateLingkunganDekomposisiDaurUlang(
        jumlahLimbarDaurUlang: Int,
        totalLimbahYangDihasilkan: Int
    ): Indikator {
        val perspektif = perspektifLingkungan
        val namaIndikator = "Dekomposisi daur ulang"
        val bobotIndikator = 50.0
        val calculation = jumlahLimbarDaurUlang / totalLimbahYangDihasilkan.toDouble()
        val standardPerformance = 50.0
        val indikator = calculateIndikator(
            calculation = calculation,
            standardPerformance = standardPerformance,
            indikator = Indikator(
                perspektif = perspektif,
                namaIndikator = namaIndikator,
                bobotIndikator = bobotIndikator
            )
        )
        perspektifLingkungan.daftarIndikator.add(indikator)
        return indikator
    }

    fun getTotalSustainibilityValue(): Result {
        var total = 0.0
        total += perspektifKeuangan.daftarIndikator.sumOf { it.hasil }
        total += perspektifKeuangan.daftarIndikator.sumOf { it.hasil }
        total += perspektifPembelajaranDanPertumbuhan.daftarIndikator.sumOf { it.hasil }
        total += perspektifProsesBisnisInternal.daftarIndikator.sumOf { it.hasil }
        total += perspektifSosial.daftarIndikator.sumOf { it.hasil }
        total += perspektifLingkungan.daftarIndikator.sumOf { it.hasil }
        return Result(
            totalScore = total,
            kategori = getKategoriSustainibility(total)
        )
    }

}

data class Result(
    var totalScore: Double = 0.0,
    var kategori: String = ""
)

data class Perspektif(
    var namaPerspektif: String = "",
    var bobotPerspektif: Double = 0.0,
    var daftarIndikator: MutableList<Indikator> = mutableListOf()
) {
    companion object {
        const val KEUANGAN = "Keuangan"
        const val KEPUASAN_PELANGGAN = "Kepuasan Pelanggan"
        const val PEMBELAJARAN_DAN_PERTUMBUHAN = "Pembelajaran dan Pertumbuhan"
        const val PROSES_BISNIS_INTERNAL = "Proses bisnis internal"
        const val SOSIAL = "Sosial"
        const val LINGKUNGAN = "Lingkungan"
    }
}

data class Indikator(
    var perspektif: Perspektif = Perspektif(),
    var namaIndikator: String = "",
    var bobotIndikator: Double = 0.0,
    var kinerjaSebenarnya: Double = 0.0,
    var kinerjaStandar: Double = 0.0,
    var tingkatKinerja: Double = 0.0,
    var hasil: Double = 0.0,
    var performansi: String = ""
)

data class Kuesioner(
    var daftarKuesioner: MutableList<ItemKuesioner> = mutableListOf()
)

fun getKuesioner(perspektif: String): MutableList<ItemKuesioner> {
    when (perspektif) {
        Perspektif.KEUANGAN -> {
            return mutableListOf(
                ItemKuesioner(
                    perspektif = Perspektif.KEUANGAN,
                    pertanyaan = "Total pendapatan usaha dalam sebulan"
                ),
                ItemKuesioner(
                    perspektif = Perspektif.KEUANGAN,
                    pertanyaan = "Total biaya usaha dalam sebulan"
                ),
                ItemKuesioner(
                    perspektif = Perspektif.KEUANGAN,
                    pertanyaan = "Volume produksi pada periode saat ini"
                ),
                ItemKuesioner(
                    perspektif = Perspektif.KEUANGAN,
                    pertanyaan = "Volume produksi pada periode sebelumnya"
                ),
                ItemKuesioner(
                    perspektif = Perspektif.KEUANGAN,
                    pertanyaan = "Total penjualan pada periode saat ini"
                ),
                ItemKuesioner(
                    perspektif = Perspektif.KEUANGAN,
                    pertanyaan = "Total penjualan pada periode sebelumnya"
                )
            )
        }
        Perspektif.KEPUASAN_PELANGGAN -> {
            return mutableListOf(
                ItemKuesioner(
                    perspektif = Perspektif.KEPUASAN_PELANGGAN,
                    pertanyaan = "Total konsumen terlayani"
                ),
                ItemKuesioner(
                    perspektif = Perspektif.KEPUASAN_PELANGGAN,
                    pertanyaan = "Total keluhan konsumen"
                ),
                ItemKuesioner(
                    perspektif = Perspektif.KEPUASAN_PELANGGAN,
                    pertanyaan = "Total konsumen lama dalam 3 bulan terakhir"
                ),
                ItemKuesioner(
                    perspektif = Perspektif.KEPUASAN_PELANGGAN,
                    pertanyaan = "Total seluruh konsumen dalam 3 bulan terakhir"
                )
            )
        }
        else -> {
            return mutableListOf(
                ItemKuesioner(
                    perspektif = Perspektif.KEPUASAN_PELANGGAN,
                    pertanyaan = "Total konsumen terlayani"
                ),
                ItemKuesioner(
                    perspektif = Perspektif.KEPUASAN_PELANGGAN,
                    pertanyaan = "Total keluhan konsumen"
                ),
                ItemKuesioner(
                    perspektif = Perspektif.KEPUASAN_PELANGGAN,
                    pertanyaan = "Total konsumen lama dalam 3 bulan terakhir"
                ),
                ItemKuesioner(
                    perspektif = Perspektif.KEPUASAN_PELANGGAN,
                    pertanyaan = "Total seluruh konsumen dalam 3 bulan terakhir"
                )
            )
        }
    }
}

data class ItemKuesioner(
    var perspektif: String = "",
    var pertanyaan: String = "",
    var jawaban: Double = 0.0
)