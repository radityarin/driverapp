package com.ftp.keberlanjutanumkmbsc.presentation.home.users.ui.home.questioner.review

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isVisible
import com.ftp.keberlanjutanumkmbsc.R
import com.ftp.keberlanjutanumkmbsc.databinding.ActivityPerspektifReviewBinding
import com.ftp.keberlanjutanumkmbsc.presentation.base.BaseActivity
import com.ftp.keberlanjutanumkmbsc.presentation.home.users.ui.home.questioner.QuestionerViewModel
import com.ftp.keberlanjutanumkmbsc.presentation.home.users.ui.home.questioner.review.dialog.ConfirmationDialog
import com.ftp.keberlanjutanumkmbsc.utils.*
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class PerspektifReviewActivity : BaseActivity(), ConfirmationDialog.OkListener {

    private var _binding: ActivityPerspektifReviewBinding? = null
    private val binding get() = _binding!!

    override fun setLayout(): View {
        _binding = ActivityPerspektifReviewBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initAppBar() {
        super.initAppBar()
        val title = "Tinjauan Isian Perspektif"
        binding.appBarLayout.tvAppBarTitle.text = title
    }

    override fun initClickListener() {
        with(binding) {
            appBarLayout.btnAppBarBack.setOnClickListener {
                onBackPressed()
            }
            tvPerspektifKeuangan.setOnClickListener {
                toggleAccordion(tvPerspektifKeuangan, llPerspektifKeuangan)
            }
            tvPerspektifPelanggan.setOnClickListener {
                toggleAccordion(tvPerspektifPelanggan, llPerspektifPelanggan)
            }
            tvPerspektifPembelajaran.setOnClickListener {
                toggleAccordion(tvPerspektifPembelajaran, llPerspektifPembelajaran)
            }
            tvPerspektifProsesBisnis.setOnClickListener {
                toggleAccordion(tvPerspektifProsesBisnis, llPerspektifProsesBisnis)
            }
            tvPerspektifSosial.setOnClickListener {
                toggleAccordion(tvPerspektifSosial, llPerspektifSosial)
            }
            tvPerspektifLingkungan.setOnClickListener {
                toggleAccordion(tvPerspektifLingkungan, llPerspektifLingkungan)
            }
            tvEnabled.setOnClickListener {
                showResultDialog()
            }
        }
    }


    private fun showResultDialog() {
        ConfirmationDialog().apply {
            setStyle(
                BottomSheetDialogFragment.STYLE_NORMAL,
                R.style.BaseBottomSheetDialogTheme
            )
            show(supportFragmentManager, ConfirmationDialog.TAG)
        }
    }

    override fun handleIntentData() {
        val listInput = intent.getListString()
        with(binding) {
            tvKeuangan1.text = listInput[0].addTwoDot()
            tvKeuangan2.text = listInput[1].addTwoDot()
            tvKeuangan3.text = listInput[2].addTwoDot()
            tvKeuangan4.text = listInput[3].addTwoDot()
            tvKeuangan5.text = listInput[4].addTwoDot()
            tvKeuangan6.text = listInput[5].addTwoDot()

            tvPelanggan1.text = listInput[6].addTwoDot()
            tvPelanggan2.text = listInput[7].addTwoDot()
            tvPelanggan3.text = listInput[8].addTwoDot()
            tvPelanggan4.text = listInput[9].addTwoDot()

            tvPembelajaran1.text = listInput[10].addTwoDot()
            tvPembelajaran2.text = listInput[11].addTwoDot()
            tvPembelajaran3.text = listInput[12].addTwoDot()
            tvPembelajaran4.text = listInput[13].addTwoDot()
            tvPembelajaran5.text = listInput[14].addTwoDot()
            tvPembelajaran6.text = listInput[15].addTwoDot()

            tvProsesBisnis1.text = listInput[16].addTwoDot()
            tvProsesBisnis2.text = listInput[17].addTwoDot()
            tvProsesBisnis3.text = listInput[18].addTwoDot()
            tvProsesBisnis4.text = listInput[19].addTwoDot()

            tvSosial1.text = listInput[20].addTwoDot()
            tvSosial2.text = listInput[21].addTwoDot()

            tvLingkungan1.text = listInput[22].addTwoDot()
            tvLingkungan2.text = listInput[23].addTwoDot()
            tvLingkungan3.text = listInput[24].addTwoDot()
            tvLingkungan4.text = listInput[25].addTwoDot()
        }
    }

    override fun onOkClickListener() {
        goToResultPerspektif(intent.getListString())
    }

}