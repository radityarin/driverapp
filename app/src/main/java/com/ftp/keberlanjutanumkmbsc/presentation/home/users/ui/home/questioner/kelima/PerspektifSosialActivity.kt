package com.ftp.keberlanjutanumkmbsc.presentation.home.users.ui.home.questioner.kelima

import android.view.View
import com.ftp.keberlanjutanumkmbsc.data.logic.Perspektif
import com.ftp.keberlanjutanumkmbsc.databinding.ActivityPerspektifSosialBinding
import com.ftp.keberlanjutanumkmbsc.presentation.base.BaseActivity
import com.ftp.keberlanjutanumkmbsc.utils.getListString
import com.ftp.keberlanjutanumkmbsc.utils.goToPerspektifKeenamLingkungan
import com.ftp.keberlanjutanumkmbsc.utils.setEditTextAndButtonListener

class PerspektifSosialActivity : BaseActivity() {

    private var _binding: ActivityPerspektifSosialBinding? = null
    private val binding get() = _binding!!

    override fun setLayout(): View {
        _binding = ActivityPerspektifSosialBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initAppBar() {
        super.initAppBar()
        val title = "Perspektif " + Perspektif.SOSIAL
        binding.appBarLayout.tvAppBarTitle.text = title
    }

    override fun initClickListener() {
        with(binding) {
            tvEnabled.setOnClickListener {
                val listInput = intent.getListString()
                listInput.addAll(
                    mutableListOf(
                        et1.text.toString(),
                        et2.text.toString()
                    )
                )
                goToPerspektifKeenamLingkungan(
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
                    et2
                ), tvEnabled, tvDisabled
            )
        }
    }
}