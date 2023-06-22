package com.project.driverapp.utils

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.project.driverapp.R
import com.project.driverapp.data.pref.ProfilePrefs
import com.project.driverapp.presentation.home.users.UserMainActivity
import com.project.driverapp.presentation.home.users.ui.home.submit.SubmitActivity
import com.project.driverapp.presentation.landing.LandingActivity
import com.project.driverapp.presentation.login.LoginActivity
import com.project.driverapp.presentation.register.RegisterActivity
import com.project.driverapp.presentation.register.dialog.AcceptJobDialog
import com.project.driverapp.presentation.register.dialog.RegisterResultDialog

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


fun showResultDialog(isSuccess: Boolean) {
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


fun Fragment.showAcceptJobDialog() {
    AcceptJobDialog().apply {
        setStyle(
            BottomSheetDialogFragment.STYLE_NORMAL,
            R.style.BaseBottomSheetDialogTheme
        )
        show(this@showAcceptJobDialog.childFragmentManager, RegisterResultDialog.TAG)
    }
}

fun Activity.goToSubmitActivity() {
    startActivity(
        Intent(
            this,
            SubmitActivity::class.java
        )
    )
    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
}