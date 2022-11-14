package com.ftp.keberlanjutanumkmbsc.presentation.home.users.ui.home.questioner.pertama

import android.view.View
import com.ftp.keberlanjutanumkmbsc.data.logic.Perspektif
import com.ftp.keberlanjutanumkmbsc.databinding.ActivityPerspektifKeuanganBinding
import com.ftp.keberlanjutanumkmbsc.presentation.base.BaseActivity
import com.ftp.keberlanjutanumkmbsc.utils.getListString
import com.ftp.keberlanjutanumkmbsc.utils.goToPerspektifKeduaPelanggan
import com.ftp.keberlanjutanumkmbsc.utils.setEditTextAndButtonListener

class PerspektifKeuanganActivity : BaseActivity() {

    private var _binding: ActivityPerspektifKeuanganBinding? = null
    private val binding get() = _binding!!

    override fun setLayout(): View {
        _binding = ActivityPerspektifKeuanganBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initAppBar() {
        super.initAppBar()
        val title = "Perspektif " + Perspektif.KEUANGAN
        binding.appBarLayout.tvAppBarTitle.text = title
    }

    override fun initClickListener() {
        with(binding) {
            tvEnabled.setOnClickListener {
                val listInput = intent.getListString()
                listInput.addAll(
                    mutableListOf(
                        et1.text.toString(),
                        et2.text.toString(),
                        et3.text.toString(),
                        et4.text.toString(),
                        et5.text.toString(),
                        et6.text.toString(),
                    )
                )
                goToPerspektifKeduaPelanggan(
                    listInput = listInput
                )
            }
            appBarLayout.btnAppBarBack.setOnClickListener {
                onBackPressed()
            }
        }
    }

    override fun initEditTextListener() {
        with(binding) {
            setEditTextAndButtonListener(
                listOf(
                    et1,
                    et2,
                    et3,
                    et4,
                    et5,
                    et6,
                ), tvEnabled, tvDisabled
            )
        }
    }
}