package com.project.driverapp.presentation.splash

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.project.driverapp.data.pref.ProfilePrefs
import com.project.driverapp.databinding.ActivitySplashBinding
import com.project.driverapp.utils.Constants.SPLASH_TIME_OUT
import com.project.driverapp.utils.goToHome
import com.project.driverapp.utils.goToLanding

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
                    goToLanding()
                }
            }
        }, SPLASH_TIME_OUT)
    }
}