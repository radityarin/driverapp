package com.ftp.keberlanjutanumkmbsc.presentation.landing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ftp.keberlanjutanumkmbsc.adapter.OnBoardingAdapter
import com.ftp.keberlanjutanumkmbsc.databinding.ActivityLandingBinding
import com.ftp.keberlanjutanumkmbsc.databinding.ActivityOnBoardingBinding
import com.ftp.keberlanjutanumkmbsc.utils.goToLogin
import com.ftp.keberlanjutanumkmbsc.utils.goToRegister

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