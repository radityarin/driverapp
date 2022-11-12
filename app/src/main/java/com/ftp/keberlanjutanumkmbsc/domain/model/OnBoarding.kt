package com.ftp.keberlanjutanumkmbsc.domain.model

import com.ftp.keberlanjutanumkmbsc.R

data class OnBoarding(
    var drawableId: Int,
    var title: String,
    var content: String
)

fun getOnBoarding(): List<OnBoarding> {
    return listOf<OnBoarding>(
        OnBoarding(
            drawableId = R.drawable.ic_onboarding_1,
            title = "Potensi UMKM",
            content = "UMKM merupakan salah satu sektor yang berkontribusi besar terhadap Produk Domestik Regional Bruto. Potensi UMKM yang begitu besar belum akan maksimal jika kinerjanya masih buruk."
        ),
        OnBoarding(
            drawableId = R.drawable.ic_onboarding_2,
            title = "Penyesuaian yang baik",
            content = "Perlu adanya penyesuaian dan perubahan pada bidang teknologi, manajemen, organisasi dan sistem produksi agar mampu berkontribusi dalam pembangunan berkelanjutan."
        ),
        OnBoarding(
            drawableId = R.drawable.ic_onboarding_3,
            title = "Buat strategi",
            content = "Indikator keberlanjutan menjadi faktor kunci dalam menjaga keunggulan kompetitif dan kesuksesan kinerja UMKM."
        )
    )
}