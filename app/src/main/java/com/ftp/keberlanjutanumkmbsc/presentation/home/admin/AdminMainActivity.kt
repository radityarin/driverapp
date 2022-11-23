package com.ftp.keberlanjutanumkmbsc.presentation.home.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ftp.keberlanjutanumkmbsc.R
import com.ftp.keberlanjutanumkmbsc.adapter.RiwayatAdapter
import com.ftp.keberlanjutanumkmbsc.databinding.ActivityAdminMainBinding
import com.ftp.keberlanjutanumkmbsc.databinding.ActivityUserMainBinding
import com.ftp.keberlanjutanumkmbsc.presentation.home.users.ui.history.HistoryViewModel
import com.ftp.keberlanjutanumkmbsc.utils.goToLanding
import org.koin.androidx.viewmodel.ext.android.viewModel

class AdminMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdminMainBinding
    private val historyViewModel: HistoryViewModel by viewModel()
    private lateinit var riwayatAdapter: RiwayatAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        historyViewModel.getRiwayatKuesioner()
        riwayatAdapter = RiwayatAdapter()
        binding.rvHistory.adapter = riwayatAdapter
        historyViewModel.listKuesionerLiveData.observe(this) {
            riwayatAdapter.submitLists(it)
        }

        binding.tvKeluar.setOnClickListener {
            goToLanding()
        }
    }

}