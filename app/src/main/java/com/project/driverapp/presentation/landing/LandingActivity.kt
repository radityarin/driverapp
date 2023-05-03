package com.project.driverapp.presentation.landing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.project.driverapp.databinding.ActivityLandingBinding
import com.project.driverapp.utils.goToLogin
import com.project.driverapp.utils.goToRegister

class LandingActivity : AppCompatActivity() {

    private var _binding: ActivityLandingBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLandingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initClick()
    }

    private fun initClick() {
        with(binding) {
            tvMasuk.setOnClickListener {
                goToLogin()
            }
            tvBuatAkun.setOnClickListener {
                goToRegister()
            }
        }
    }

}