package com.ftp.keberlanjutanumkmbsc.utils

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.view.WindowManager
import com.ftp.keberlanjutanumkmbsc.R
import com.ftp.keberlanjutanumkmbsc.presentation.landing.LandingActivity
import com.ftp.keberlanjutanumkmbsc.presentation.login.LoginActivity
import com.ftp.keberlanjutanumkmbsc.presentation.register.RegisterActivity

fun Activity.goToLanding() {
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
            RegisterActivity::class.java
        )
    )
    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
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