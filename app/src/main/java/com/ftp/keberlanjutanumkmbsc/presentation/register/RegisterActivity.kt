package com.ftp.keberlanjutanumkmbsc.presentation.register

import android.view.View
import com.ftp.keberlanjutanumkmbsc.R
import com.ftp.keberlanjutanumkmbsc.databinding.ActivityRegisterBinding
import com.ftp.keberlanjutanumkmbsc.presentation.base.BaseActivity
import com.ftp.keberlanjutanumkmbsc.presentation.register.dialog.RegisterResultDialog
import com.ftp.keberlanjutanumkmbsc.utils.goToHome
import com.ftp.keberlanjutanumkmbsc.utils.goToLogin
import com.ftp.keberlanjutanumkmbsc.utils.setEditTextAndButtonAndCheckBoxAndSpinnerListener
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterActivity : BaseActivity(), RegisterResultDialog.OkListener {

    private var _binding: ActivityRegisterBinding? = null
    private val binding get() = _binding!!
    private val registerViewModel: RegisterViewModel by viewModel()

    override fun setLayout(): View {
        _binding = ActivityRegisterBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initObserver() {
        super.initBaseObserver(registerViewModel)
        registerViewModel.registerSuccessLiveData.observe(this) {
            showResultDialog(it)
        }
    }

    override fun initClickListener() {
        with(binding) {
            tvEnabled.setOnClickListener {
                registerViewModel.register(
                    namaLengkap = etNamaLengkap.text.toString(),
                    jenisKelamin = spnJenisKelamin.selectedItem.toString(),
                    pekerjaan = etPekerjaan.text.toString(),
                    namaUsaha = etAlamat.text.toString(),
                    alamat = etNamaUsaha.text.toString(),
                    nomorHP = etNoHP.text.toString(),
                    pengalamanUsahaTahun = etPengalamanUsaha.text.toString(),
                    jenisProdukYangDijual = etJenisProduk.text.toString(),
                    kataSandi = etKataSandi.text.toString(),
                    konfirmasiKataSandi = etKonfirmasiSandi.text.toString()
                )
            }
            tvMasuk.setOnClickListener {
                goToLogin()
            }
            tvBack.setOnClickListener {
                onBackPressed()
            }
        }
    }

    override fun initEditTextListener() {
        with(binding) {
            setEditTextAndButtonAndCheckBoxAndSpinnerListener(
                listOf(
                    etNamaLengkap,
                    etPekerjaan,
                    etNamaUsaha,
                    etAlamat,
                    etNoHP,
                    etPengalamanUsaha,
                    etJenisProduk,
                    etKataSandi,
                    etKonfirmasiSandi
                ), checkBox,
                listOf(spnJenisKelamin), tvEnabled, tvDisabled
            )
        }
    }

    private fun showResultDialog(isSuccess: Boolean) {
        RegisterResultDialog(
            isSuccess = isSuccess
        ).apply {
            setStyle(
                BottomSheetDialogFragment.STYLE_NORMAL,
                R.style.BaseBottomSheetDialogTheme
            )
            show(supportFragmentManager, RegisterResultDialog.TAG)
        }
    }

    override fun onOkClickListener(isSuccess: Boolean) {
        if (isSuccess) {
            goToHome()
        }
    }

}