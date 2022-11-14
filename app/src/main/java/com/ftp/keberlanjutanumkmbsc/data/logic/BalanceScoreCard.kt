package com.ftp.keberlanjutanumkmbsc.data.logic

class BalanceScoreCard {

    val perspektifKeuangan = Perspektif()
    val perspektifKepuasanPelanggan = Perspektif()
    val perspektifPembelajaranDanPertumbuhan = Perspektif()
    val perspektifProsesBisnisDoubleernal = Perspektif()
    val perspektifSosial = Perspektif()
    val perspektifLingkungan = Perspektif()

    var kategoriPoor = "Poor"
    var kategoriMarginal = "Marginal"
    var kategoriAverage = "Average"
    var kategoriGood = "Good"
    var kategoriExcellent = "Excellent"
    var kategoriNotFound = "Not found"

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
        perspektifProsesBisnisDoubleernal.apply {
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
                30.0
            }
            else -> {
                30.0
            }
        }
    }

    fun getKategoriSustainibility(score: Double): String {
        return when {
            score <= 40 -> {
                kategoriPoor
            }
            score > 40 && score <= 50 -> {
                kategoriMarginal
            }
            score > 50 && score <= 70 -> {
                kategoriAverage
            }
            score > 70 && score <= 90 -> {
                kategoriGood
            }
            score > 90 && score <= 100 -> {
                kategoriExcellent
            }
            else -> {
                kategoriNotFound
            }
        }
    }

    private fun getTingkatKinerjaKategori(actual: Double, standard: Double): String {
        return when (getTingkatKinerja(actual, standard)) {
            30.0 -> {
                "Weak"
            }
            60.0 -> {
                "Good"
            }
            90.0 -> {
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
        indikator: Indikator,
        calculateName: String = ""
    ): Indikator {
        val actualPerformance = calculation * 100.0
        val nilaiTingkatKinerja = getTingkatKinerja(actualPerformance, standardPerformance)
        val nilaiTingkatKinerjaKategori =
            getTingkatKinerjaKategori(actualPerformance, standardPerformance)
        val result =
            (indikator.perspektif.bobotPerspektif / 100) * (indikator.bobotIndikator / 100) * nilaiTingkatKinerja
        println("UMKMBSC-ActualPerformance$calculateName $actualPerformance")
        println("UMKMBSC-StandardPerformance$calculateName $standardPerformance")
        println("UMKMBSC-nilaiTingkatKinerja$calculateName $nilaiTingkatKinerja")
        println("UMKMBSC-nilaiTingkatKinerjaKategori$calculateName $nilaiTingkatKinerjaKategori")
        println("UMKMBSC-SistemHasil$calculateName ${indikator.perspektif.bobotPerspektif / 100} * ${indikator.bobotIndikator / 100} * $nilaiTingkatKinerja = $result")
        indikator.apply {
            kinerjaSebenarnya = actualPerformance
            kinerjaStandar = standardPerformance
            tingkatKinerja = nilaiTingkatKinerja
            tingkatKinerjaKategori = nilaiTingkatKinerjaKategori
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

    fun insertPerspektifKeuangan(
        totalPendapatan: Double,
        totalBiaya: Double,
        volumeProduksiSekarang: Double,
        volumeProduksiSebelumnya: Double,
        penjualanSekarang: Double,
        penjualanSebelumnya: Double
    ): MutableList<Indikator> {
        val calculateKeuanganKeuntungan = calculateKeuanganKeuntungan(
            totalPendapatan = totalPendapatan,
            totalBiaya = totalBiaya
        )
        val calculateKeuanganPertumbuhanVolumeProduksi = calculateKeuanganPertumbuhanVolumeProduksi(
            volumeProduksiSekarang = volumeProduksiSekarang,
            volumeProduksiSebelumnya = volumeProduksiSebelumnya
        )
        val calculateKeuanganPertumbuhanPenjualan = calculateKeuanganPertumbuhanPenjualan(
            penjualanSekarang = penjualanSekarang,
            penjualanSebelumnya = penjualanSebelumnya
        )
        return mutableListOf(
            calculateKeuanganKeuntungan,
            calculateKeuanganPertumbuhanVolumeProduksi,
            calculateKeuanganPertumbuhanPenjualan
        )
    }

    fun calculateKeuanganKeuntungan(totalPendapatan: Double, totalBiaya: Double): Indikator {
        val perspektif = perspektifKeuangan
        val namaIndikator = "Keuangan"
        val bobotIndikator = 35.0
        val keuntungan = (totalPendapatan - totalBiaya)
        val calculation = keuntungan / totalPendapatan
        val standardPerformance = 50.0
        val indikator = calculateIndikator(
            calculation = calculation,
            standardPerformance = standardPerformance,
            indikator = Indikator(
                perspektif = perspektif,
                namaIndikator = namaIndikator,
                bobotIndikator = bobotIndikator
            ),
            calculateName = "KeuanganKeuntungan"
        )
        perspektifKeuangan.daftarIndikator.add(indikator)
        return indikator
    }

    fun calculateKeuanganPertumbuhanVolumeProduksi(
        volumeProduksiSekarang: Double,
        volumeProduksiSebelumnya: Double
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
        penjualanSekarang: Double,
        penjualanSebelumnya: Double
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

    fun insertPerspektifKepuasanPelanggan(
        totalKonsumenTerlayani: Double,
        totalKeluhanKonsumen: Double,
        totalKonsumenLama: Double,
        totalKonsumenTigaBulanTerakhir: Double
    ): MutableList<Indikator> {
        val calculateKepuasanKeluhanPelanggan = calculateKepuasanKeluhanPelanggan(
            totalKonsumenTerlayani = totalKonsumenTerlayani,
            totalKeluhanKonsumen = totalKeluhanKonsumen
        )
        val calculateKepuasanCustomerRetention = calculateKepuasanCustomerRetention(
            totalKonsumenLama = totalKonsumenLama,
            totalKonsumenTigaBulanTerakhir = totalKonsumenTigaBulanTerakhir
        )
        return mutableListOf(
            calculateKepuasanKeluhanPelanggan,
            calculateKepuasanCustomerRetention
        )
    }

    fun calculateKepuasanKeluhanPelanggan(
        totalKonsumenTerlayani: Double,
        totalKeluhanKonsumen: Double
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
        totalKonsumenLama: Double,
        totalKonsumenTigaBulanTerakhir: Double
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

    fun insertPerspektifPembelajaranDanPertumbuhan(
        totalKaryawanYangMengikutiPelatihan: Double,
        totalKaryawan: Double,
        totalKeluhanTerlayani: Double,
        totalKeluhanKaryawan: Double,
        hasilKerjaKaryawan: Double,
        jamKerja: Double
    ): MutableList<Indikator> {
        val calculatePembelajaranPelatihanKaryawan = calculatePembelajaranPelatihanKaryawan(
            totalKaryawanYangMengikutiPelatihan = totalKaryawanYangMengikutiPelatihan,
            totalKaryawan = totalKaryawan
        )
        val calculatePembelajaranKeluhanKaryawan = calculatePembelajaranKeluhanKaryawan(
            totalKeluhanTerlayani = totalKeluhanTerlayani,
            totalKeluhanKaryawan = totalKeluhanKaryawan
        )
        val calculatePembelajaranProduktivitasKaryawan = calculatePembelajaranProduktivitasKaryawan(
            hasilKerjaKaryawan = hasilKerjaKaryawan,
            jamKerja = jamKerja
        )
        return mutableListOf(
            calculatePembelajaranPelatihanKaryawan,
            calculatePembelajaranKeluhanKaryawan,
            calculatePembelajaranProduktivitasKaryawan
        )
    }

    fun calculatePembelajaranPelatihanKaryawan(
        totalKaryawanYangMengikutiPelatihan: Double,
        totalKaryawan: Double
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
        totalKeluhanTerlayani: Double,
        totalKeluhanKaryawan: Double
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
        hasilKerjaKaryawan: Double,
        jamKerja: Double
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

    fun insertPerspektifProsesBisnisInternal(
        totalProdukYangBaik: Double,
        totalProduk: Double,
        totalProgramTerlaksana: Double,
        totalRencanaProgram: Double,
    ): MutableList<Indikator> {
        val calculateBisnisKualitasProduk = calculateBisnisKualitasProduk(
            totalProdukYangBaik = totalProdukYangBaik,
            totalProduk = totalProduk
        )
        val calculateBisnisImplementasiProsentaseProgram =
            calculateBisnisImplementasiProsentaseProgram(
                totalProgramTerlaksana = totalProgramTerlaksana,
                totalRencanaProgram = totalRencanaProgram
            )
        return mutableListOf(
            calculateBisnisKualitasProduk,
            calculateBisnisImplementasiProsentaseProgram,
        )
    }

    fun calculateBisnisKualitasProduk(totalProdukYangBaik: Double, totalProduk: Double): Indikator {
        val perspektif = perspektifProsesBisnisDoubleernal
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
        perspektifProsesBisnisDoubleernal.daftarIndikator.add(indikator)
        return indikator
    }

    fun calculateBisnisImplementasiProsentaseProgram(
        totalProgramTerlaksana: Double,
        totalRencanaProgram: Double
    ): Indikator {
        val perspektif = perspektifProsesBisnisDoubleernal
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
        perspektifProsesBisnisDoubleernal.daftarIndikator.add(indikator)
        return indikator
    }

    /**
     * PERSPEKTIF SOSIAL
     * 1. Peluang pekerjaan - 100%
     */

    fun insertPerspektifSosial(
        jumlahKaryawanMasyarakatSekitar: Double,
        jumlahKaryawan: Double
    ): MutableList<Indikator> {
        val calculateSosialPeluangPekerjaan = calculateSosialPeluangPekerjaan(
            jumlahKaryawanMasyarakatSekitar = jumlahKaryawanMasyarakatSekitar,
            jumlahKaryawan = jumlahKaryawan
        )
        return mutableListOf(
            calculateSosialPeluangPekerjaan,
        )
    }

    fun calculateSosialPeluangPekerjaan(
        jumlahKaryawanMasyarakatSekitar: Double,
        jumlahKaryawan: Double
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

    fun insertPerspektifLingkungan(
        produkDaurUlang: Double,
        jumlahProduk: Double,
        jumlahLimbarDaurUlang: Double,
        totalLimbahYangDihasilkan: Double
    ): MutableList<Indikator> {
        val calculateLingkunganProdukDaurUlang = calculateLingkunganProdukDaurUlang(
            produkDaurUlang = produkDaurUlang,
            jumlahProduk = jumlahProduk
        )
        val calculateLingkunganDekomposisiDaurUlang = calculateLingkunganDekomposisiDaurUlang(
            jumlahLimbarDaurUlang = jumlahLimbarDaurUlang,
            totalLimbahYangDihasilkan = totalLimbahYangDihasilkan
        )
        return mutableListOf(
            calculateLingkunganProdukDaurUlang,
            calculateLingkunganDekomposisiDaurUlang
        )
    }

    fun calculateLingkunganProdukDaurUlang(
        produkDaurUlang: Double,
        jumlahProduk: Double
    ): Indikator {
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
        jumlahLimbarDaurUlang: Double,
        totalLimbahYangDihasilkan: Double
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
        total += perspektifProsesBisnisDoubleernal.daftarIndikator.sumOf { it.hasil }
        total += perspektifSosial.daftarIndikator.sumOf { it.hasil }
        total += perspektifLingkungan.daftarIndikator.sumOf { it.hasil }
        return Result(
            totalScore = total,
            kategori = getKategoriSustainibility(total)
        )
    }


    fun getStrategi(tipePerspektif: String, skorKategori: String): List<String> {
        return when (tipePerspektif) {
            Perspektif.KEUANGAN -> {
                when (skorKategori) {
                    kategoriPoor -> {
                        listOf(
                            "Ini adalah strategi satu",
                            "Ini adalah strategi dua",
                            "Ini adalah strategi tiga",
                            "Ini adalah strategi empat",
                        )
                    }
                    kategoriMarginal -> {
                        listOf(
                            "Ini adalah strategi satu",
                            "Ini adalah strategi dua",
                            "Ini adalah strategi tiga",
                            "Ini adalah strategi empat",
                        )
                    }
                    kategoriAverage -> {
                        listOf(
                            "Ini adalah strategi satu",
                            "Ini adalah strategi dua",
                            "Ini adalah strategi tiga",
                            "Ini adalah strategi empat",
                        )
                    }
                    kategoriGood -> {
                        listOf(
                            "Ini adalah strategi satu",
                            "Ini adalah strategi dua",
                            "Ini adalah strategi tiga",
                            "Ini adalah strategi empat",
                        )
                    }
                    kategoriExcellent -> {
                        listOf(
                            "Ini adalah strategi satu",
                            "Ini adalah strategi dua",
                            "Ini adalah strategi tiga",
                            "Ini adalah strategi empat",
                        )
                    }
                    kategoriNotFound -> {
                        listOf(
                            "Ini adalah strategi satu",
                            "Ini adalah strategi dua",
                            "Ini adalah strategi tiga",
                            "Ini adalah strategi empat",
                        )
                    }
                    else -> {
                        emptyList()
                    }
                }
            }
            Perspektif.KEPUASAN_PELANGGAN -> {
                when (skorKategori) {
                    kategoriPoor -> {
                        listOf(
                            "Ini adalah strategi satu",
                            "Ini adalah strategi dua",
                            "Ini adalah strategi tiga",
                            "Ini adalah strategi empat",
                        )
                    }
                    kategoriMarginal -> {
                        listOf(
                            "Ini adalah strategi satu",
                            "Ini adalah strategi dua",
                            "Ini adalah strategi tiga",
                            "Ini adalah strategi empat",
                        )
                    }
                    kategoriAverage -> {
                        listOf(
                            "Ini adalah strategi satu",
                            "Ini adalah strategi dua",
                            "Ini adalah strategi tiga",
                            "Ini adalah strategi empat",
                        )
                    }
                    kategoriGood -> {
                        listOf(
                            "Ini adalah strategi satu",
                            "Ini adalah strategi dua",
                            "Ini adalah strategi tiga",
                            "Ini adalah strategi empat",
                        )
                    }
                    kategoriExcellent -> {
                        listOf(
                            "Ini adalah strategi satu",
                            "Ini adalah strategi dua",
                            "Ini adalah strategi tiga",
                            "Ini adalah strategi empat",
                        )
                    }
                    kategoriNotFound -> {
                        listOf(
                            "Ini adalah strategi satu",
                            "Ini adalah strategi dua",
                            "Ini adalah strategi tiga",
                            "Ini adalah strategi empat",
                        )
                    }
                    else -> {
                        emptyList()
                    }
                }
            }
            Perspektif.PEMBELAJARAN_DAN_PERTUMBUHAN -> {
                when (skorKategori) {
                    kategoriPoor -> {
                        listOf(
                            "Ini adalah strategi satu",
                            "Ini adalah strategi dua",
                            "Ini adalah strategi tiga",
                            "Ini adalah strategi empat",
                        )
                    }
                    kategoriMarginal -> {
                        listOf(
                            "Ini adalah strategi satu",
                            "Ini adalah strategi dua",
                            "Ini adalah strategi tiga",
                            "Ini adalah strategi empat",
                        )
                    }
                    kategoriAverage -> {
                        listOf(
                            "Ini adalah strategi satu",
                            "Ini adalah strategi dua",
                            "Ini adalah strategi tiga",
                            "Ini adalah strategi empat",
                        )
                    }
                    kategoriGood -> {
                        listOf(
                            "Ini adalah strategi satu",
                            "Ini adalah strategi dua",
                            "Ini adalah strategi tiga",
                            "Ini adalah strategi empat",
                        )
                    }
                    kategoriExcellent -> {
                        listOf(
                            "Ini adalah strategi satu",
                            "Ini adalah strategi dua",
                            "Ini adalah strategi tiga",
                            "Ini adalah strategi empat",
                        )
                    }
                    kategoriNotFound -> {
                        listOf(
                            "Ini adalah strategi satu",
                            "Ini adalah strategi dua",
                            "Ini adalah strategi tiga",
                            "Ini adalah strategi empat",
                        )
                    }
                    else -> {
                        emptyList()
                    }
                }
            }
            Perspektif.PROSES_BISNIS_INTERNAL -> {
                when (skorKategori) {
                    kategoriPoor -> {
                        listOf(
                            "Ini adalah strategi satu",
                            "Ini adalah strategi dua",
                            "Ini adalah strategi tiga",
                            "Ini adalah strategi empat",
                        )
                    }
                    kategoriMarginal -> {
                        listOf(
                            "Ini adalah strategi satu",
                            "Ini adalah strategi dua",
                            "Ini adalah strategi tiga",
                            "Ini adalah strategi empat",
                        )
                    }
                    kategoriAverage -> {
                        listOf(
                            "Ini adalah strategi satu",
                            "Ini adalah strategi dua",
                            "Ini adalah strategi tiga",
                            "Ini adalah strategi empat",
                        )
                    }
                    kategoriGood -> {
                        listOf(
                            "Ini adalah strategi satu",
                            "Ini adalah strategi dua",
                            "Ini adalah strategi tiga",
                            "Ini adalah strategi empat",
                        )
                    }
                    kategoriExcellent -> {
                        listOf(
                            "Ini adalah strategi satu",
                            "Ini adalah strategi dua",
                            "Ini adalah strategi tiga",
                            "Ini adalah strategi empat",
                        )
                    }
                    kategoriNotFound -> {
                        listOf(
                            "Ini adalah strategi satu",
                            "Ini adalah strategi dua",
                            "Ini adalah strategi tiga",
                            "Ini adalah strategi empat",
                        )
                    }
                    else -> {
                        emptyList()
                    }
                }
            }
            Perspektif.SOSIAL -> {
                when (skorKategori) {
                    kategoriPoor -> {
                        listOf(
                            "Ini adalah strategi satu",
                            "Ini adalah strategi dua",
                            "Ini adalah strategi tiga",
                            "Ini adalah strategi empat",
                        )
                    }
                    kategoriMarginal -> {
                        listOf(
                            "Ini adalah strategi satu",
                            "Ini adalah strategi dua",
                            "Ini adalah strategi tiga",
                            "Ini adalah strategi empat",
                        )
                    }
                    kategoriAverage -> {
                        listOf(
                            "Ini adalah strategi satu",
                            "Ini adalah strategi dua",
                            "Ini adalah strategi tiga",
                            "Ini adalah strategi empat",
                        )
                    }
                    kategoriGood -> {
                        listOf(
                            "Ini adalah strategi satu",
                            "Ini adalah strategi dua",
                            "Ini adalah strategi tiga",
                            "Ini adalah strategi empat",
                        )
                    }
                    kategoriExcellent -> {
                        listOf(
                            "Ini adalah strategi satu",
                            "Ini adalah strategi dua",
                            "Ini adalah strategi tiga",
                            "Ini adalah strategi empat",
                        )
                    }
                    kategoriNotFound -> {
                        listOf(
                            "Ini adalah strategi satu",
                            "Ini adalah strategi dua",
                            "Ini adalah strategi tiga",
                            "Ini adalah strategi empat",
                        )
                    }
                    else -> {
                        emptyList()
                    }
                }
            }
            Perspektif.LINGKUNGAN -> {
                when (skorKategori) {
                    kategoriPoor -> {
                        listOf(
                            "Ini adalah strategi satu",
                            "Ini adalah strategi dua",
                            "Ini adalah strategi tiga",
                            "Ini adalah strategi empat",
                        )
                    }
                    kategoriMarginal -> {
                        listOf(
                            "Ini adalah strategi satu",
                            "Ini adalah strategi dua",
                            "Ini adalah strategi tiga",
                            "Ini adalah strategi empat",
                        )
                    }
                    kategoriAverage -> {
                        listOf(
                            "Ini adalah strategi satu",
                            "Ini adalah strategi dua",
                            "Ini adalah strategi tiga",
                            "Ini adalah strategi empat",
                        )
                    }
                    kategoriGood -> {
                        listOf(
                            "Ini adalah strategi satu",
                            "Ini adalah strategi dua",
                            "Ini adalah strategi tiga",
                            "Ini adalah strategi empat",
                        )
                    }
                    kategoriExcellent -> {
                        listOf(
                            "Ini adalah strategi satu",
                            "Ini adalah strategi dua",
                            "Ini adalah strategi tiga",
                            "Ini adalah strategi empat",
                        )
                    }
                    kategoriNotFound -> {
                        listOf(
                            "Ini adalah strategi satu",
                            "Ini adalah strategi dua",
                            "Ini adalah strategi tiga",
                            "Ini adalah strategi empat",
                        )
                    }
                    else -> {
                        emptyList()
                    }
                }
            }
            else -> {
                emptyList()
            }
        }
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
        const val PROSES_BISNIS_INTERNAL = "Proses bisnis Doubleernal"
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
    var tingkatKinerjaKategori: String = "",
    var hasil: Double = 0.0,
    var performansi: String = ""
)

data class Kuesioner(
    var namaKuesioner: String? = null,
    var idKuesioner: String = "",
    var idUser: String = "",
    var tanggal: String = "",
    var skor: Double = 0.0,
    var skorKategori: String = "",
    var indikatorKeuangan: List<Double> = mutableListOf(),
    var indikatorKepuasan: List<Double> = mutableListOf(),
    var indikatorPembelajaran: List<Double> = mutableListOf(),
    var indikatorProsesBisnis: List<Double> = mutableListOf(),
    var indikatorSosial: List<Double> = mutableListOf(),
    var indikatorLingkungan: List<Double> = mutableListOf(),
    var strategiKeuangan: List<String> = mutableListOf(),
    var strategiKepuasan: List<String> = mutableListOf(),
    var strategiPembelajaran: List<String> = mutableListOf(),
    var strategiProsesBisnis: List<String> = mutableListOf(),
    var strategiSosial: List<String> = mutableListOf(),
    var strategiLingkungan: List<String> = mutableListOf(),
) {
    constructor() : this(
        "",
        "",
        "",
        "",
        0.0,
        "",
        emptyList(),
        emptyList(),
        emptyList(),
        emptyList(),
        emptyList(),
        emptyList(),
        emptyList(),
        emptyList(),
        emptyList(),
        emptyList(),
        emptyList(),
        emptyList()
    )
}

data class KuesionerByDate(
    var tanggal: String,
    var listKuesioner: List<Kuesioner>,
)

data class Strategi(
    var number: String,
    var strategi: String
)