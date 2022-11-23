package com.ftp.keberlanjutanumkmbsc.presentation.splash

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.ftp.keberlanjutanumkmbsc.data.pref.ProfilePrefs
import com.ftp.keberlanjutanumkmbsc.databinding.ActivitySplashBinding
import com.ftp.keberlanjutanumkmbsc.utils.Constants.SPLASH_TIME_OUT
import com.ftp.keberlanjutanumkmbsc.utils.goToHome
import com.ftp.keberlanjutanumkmbsc.utils.goToOnBoarding

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private var _binding: ActivitySplashBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySplashBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        Handler(Looper.getMainLooper()).postDelayed({
            when {
                ProfilePrefs.idFirebase.isNotEmpty() -> {
                    goToHome()
                }
                else -> {
                    goToOnBoarding()
                }
            }
        }, SPLASH_TIME_OUT)
    }
}