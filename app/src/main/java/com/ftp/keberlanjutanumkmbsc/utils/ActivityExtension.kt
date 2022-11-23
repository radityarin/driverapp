package com.ftp.keberlanjutanumkmbsc.utils

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.view.WindowManager
import com.ftp.keberlanjutanumkmbsc.R
import com.ftp.keberlanjutanumkmbsc.data.pref.ProfilePrefs
import com.ftp.keberlanjutanumkmbsc.presentation.home.admin.AdminMainActivity
import com.ftp.keberlanjutanumkmbsc.presentation.home.users.UserMainActivity
import com.ftp.keberlanjutanumkmbsc.presentation.home.users.ui.home.questioner.kedua.PerspektifPelangganActivity
import com.ftp.keberlanjutanumkmbsc.presentation.home.users.ui.home.questioner.keempat.PerspektifBisnisActivity
import com.ftp.keberlanjutanumkmbsc.presentation.home.users.ui.home.questioner.keenam.PerspektifLingkunganActivity
import com.ftp.keberlanjutanumkmbsc.presentation.home.users.ui.home.questioner.kelima.PerspektifSosialActivity
import com.ftp.keberlanjutanumkmbsc.presentation.home.users.ui.home.questioner.ketiga.PerspektifPembelajaranActivity
import com.ftp.keberlanjutanumkmbsc.presentation.home.users.ui.home.questioner.pertama.PerspektifKeuanganActivity
import com.ftp.keberlanjutanumkmbsc.presentation.home.users.ui.home.questioner.result.ResultActivity
import com.ftp.keberlanjutanumkmbsc.presentation.home.users.ui.home.questioner.review.PerspektifReviewActivity
import com.ftp.keberlanjutanumkmbsc.presentation.landing.LandingActivity
import com.ftp.keberlanjutanumkmbsc.presentation.login.LoginActivity
import com.ftp.keberlanjutanumkmbsc.presentation.onboarding.OnBoardingActivity
import com.ftp.keberlanjutanumkmbsc.presentation.register.RegisterActivity
import com.ftp.keberlanjutanumkmbsc.presentation.register.dialog.RegisterResultDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

fun Activity.goToOnBoarding() {
    ProfilePrefs.clear()
    finishAffinity()
    startActivity(
        Intent(
            this,
            OnBoardingActivity::class.java
        )
    )
    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
}

fun Activity.goToLanding() {
    ProfilePrefs.clear()
    finishAffinity()
    startActivity(
        Intent(
            this,
            LandingActivity::class.java
        )
    )
    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
}

fun Activity.goToLogin() {
    startActivity(
        Intent(
            this,
            LoginActivity::class.java
        )
    )
    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
}

fun Activity.goToRegister() {
    startActivity(
        Intent(
            this,
            RegisterActivity::class.java
        )
    )
    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
}

fun Activity.goToHome() {
    startActivity(
        Intent(
            this,
            UserMainActivity::class.java
        )
    )
    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    finishAffinity()
}

fun Activity.goToHomeAdmin() {
    startActivity(
        Intent(
            this,
            AdminMainActivity::class.java
        )
    )
    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    finishAffinity()
}

fun Activity.showLoadingDialog(): Dialog {
    val dialog = Dialog(this)
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    val view = layoutInflater.inflate(R.layout.dialog_loading, null)
    dialog.setContentView(view)
    dialog.window?.setLayout(
        WindowManager.LayoutParams.WRAP_CONTENT,
        WindowManager.LayoutParams.WRAP_CONTENT
    )
    dialog.setCancelable(false)
    dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    dialog.show()
    return dialog
}


fun Activity.showResultDialog(isSuccess: Boolean) {
    RegisterResultDialog(
        isSuccess = isSuccess
    ).apply {
        setStyle(
            BottomSheetDialogFragment.STYLE_NORMAL,
            R.style.BaseBottomSheetDialogTheme
        )
        show(childFragmentManager, RegisterResultDialog.TAG)
    }
}

fun Activity.goToPerspektifPertamaKeuangan() {
    startActivity(
        Intent(
            this,
            PerspektifKeuanganActivity::class.java
        )
    )
    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
}

fun Activity.goToPerspektifKeduaPelanggan(listInput: MutableList<String>) {
    startActivity(
        Intent(
            this,
            PerspektifPelangganActivity::class.java
        ).apply {
            setListString(listInput)
        }
    )
    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
}

fun Activity.goToPerspektifKetigaPembelajaran(listInput: MutableList<String>) {
    startActivity(
        Intent(
            this,
            PerspektifPembelajaranActivity::class.java
        ).apply {
            setListString(listInput)
        }
    )
    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
}

fun Activity.goToPerspektifKeempatProsesBisnis(listInput: MutableList<String>) {
    startActivity(
        Intent(
            this,
            PerspektifBisnisActivity::class.java
        ).apply {
            setListString(listInput)
        }
    )
    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
}

fun Activity.goToPerspektifKelimaSosial(listInput: MutableList<String>) {
    startActivity(
        Intent(
            this,
            PerspektifSosialActivity::class.java
        ).apply {
            setListString(listInput)
        }
    )
    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
}

fun Activity.goToPerspektifKeenamLingkungan(listInput: MutableList<String>) {
    startActivity(
        Intent(
            this,
            PerspektifLingkunganActivity::class.java
        ).apply {
            setListString(listInput)
        }
    )
    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
}

fun Activity.goToReviewPerspektif(listInput: MutableList<String>) {
    startActivity(
        Intent(
            this,
            PerspektifReviewActivity::class.java
        ).apply {
            setListString(listInput)
        }
    )
    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
}

fun Activity.goToResultPerspektif(listInput: MutableList<String>) {
    startActivity(
        Intent(
            this,
            ResultActivity::class.java
        ).apply {
            setListString(listInput)
        }
    )
    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
}