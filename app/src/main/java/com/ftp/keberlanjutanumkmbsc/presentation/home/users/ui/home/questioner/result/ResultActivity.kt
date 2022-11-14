package com.ftp.keberlanjutanumkmbsc.presentation.home.users.ui.home.questioner.result

import android.view.View
import com.ftp.keberlanjutanumkmbsc.adapter.ItemStrategiAdapter
import com.ftp.keberlanjutanumkmbsc.databinding.ActivityResultBinding
import com.ftp.keberlanjutanumkmbsc.presentation.base.BaseActivity
import com.ftp.keberlanjutanumkmbsc.presentation.home.users.ui.home.questioner.QuestionerViewModel
import com.ftp.keberlanjutanumkmbsc.utils.*
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
import org.koin.androidx.viewmodel.ext.android.viewModel


class ResultActivity : BaseActivity() {

    private var _binding: ActivityResultBinding? = null
    private val binding get() = _binding!!
    private val questionerViewModel: QuestionerViewModel by viewModel()
    private lateinit var itemStrategiKeuanganAdapter: ItemStrategiAdapter
    private lateinit var itemStrategiKepuasanAdapter: ItemStrategiAdapter
    private lateinit var itemStrategiPembelajaranAdapter: ItemStrategiAdapter
    private lateinit var itemStrategiProsesBisnisAdapter: ItemStrategiAdapter
    private lateinit var itemStrategiSosialAdapter: ItemStrategiAdapter
    private lateinit var itemStrategiLingkunganAdapter: ItemStrategiAdapter

    override fun setLayout(): View {
        _binding = ActivityResultBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initClickListener() {
        with(binding.bottomSheet) {
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
            tvStrategi.setOnClickListener {
                toggleAccordion(tvStrategi, llPerspektif, false)
            }
            tvBackToHome.setOnClickListener {
                goToHome()
            }
        }
    }

    override fun initObserver() {
        super.initBaseObserver(questionerViewModel)

        questionerViewModel.listIndikatorKeuangan.observe(this) {
            with(binding.bottomSheet) {
                tvKeuangan1.text = it[0].hasil.toTwoNumberBehindComma()
                tvKeuangan2.text = it[1].hasil.toTwoNumberBehindComma()
                tvKeuangan3.text = it[2].hasil.toTwoNumberBehindComma()
            }
        }

        questionerViewModel.listIndikatorKepuasan.observe(this) {
            with(binding.bottomSheet) {
                tvPelanggan1.text = it[0].hasil.toTwoNumberBehindComma()
                tvPelanggan2.text = it[1].hasil.toTwoNumberBehindComma()
            }
        }

        questionerViewModel.listIndikatorPembelajaran.observe(this) {
            with(binding.bottomSheet) {
                tvPembelajaran1.text = it[0].hasil.toTwoNumberBehindComma()
                tvPembelajaran2.text = it[1].hasil.toTwoNumberBehindComma()
                tvPembelajaran3.text = it[2].hasil.toTwoNumberBehindComma()
            }
        }

        questionerViewModel.listIndikatorProsesBisnis.observe(this) {
            with(binding.bottomSheet) {
                tvProsesBisnis1.text = it[0].hasil.toTwoNumberBehindComma()
                tvProsesBisnis2.text = it[1].hasil.toTwoNumberBehindComma()
            }
        }

        questionerViewModel.listIndikatorSosial.observe(this) {
            with(binding.bottomSheet) {
                tvSosial1.text = it[0].hasil.toTwoNumberBehindComma()
            }
        }

        questionerViewModel.listIndikatorLingkungan.observe(this) {
            with(binding.bottomSheet) {
                tvLingkungan1.text = it[0].hasil.toTwoNumberBehindComma()
                tvLingkungan2.text = it[0].hasil.toTwoNumberBehindComma()
            }
        }

        questionerViewModel.scoreLiveData.observe(this) {
            with(binding) {
                tvSkor.text = it.toString()
            }
        }

        questionerViewModel.scoreCategoryLiveData.observe(this) {
            with(binding) {
                tvKategori.text = it.toString()
            }
        }

        questionerViewModel.listStrategiKeuangan.observe(this) {
            with(binding) {
                itemStrategiKeuanganAdapter.submitList(it)
            }
        }

        questionerViewModel.listStrategiKepuasan.observe(this) {
            with(binding) {
                itemStrategiKepuasanAdapter.submitList(it)
            }
        }

        questionerViewModel.listStrategiPembelajaran.observe(this) {
            with(binding) {
                itemStrategiPembelajaranAdapter.submitList(it)
            }
        }

        questionerViewModel.listStrategiProsesBisnis.observe(this) {
            with(binding) {
                itemStrategiProsesBisnisAdapter.submitList(it)
            }
        }

        questionerViewModel.listStrategiSosial.observe(this) {
            with(binding) {
                itemStrategiSosialAdapter.submitList(it)
            }
        }

        questionerViewModel.listStrategiLingkungan.observe(this) {
            with(binding) {
                itemStrategiLingkunganAdapter.submitList(it)
            }
        }
    }

    override fun handleIntentData() {
        super.handleIntentData()
        val listInput = intent.getListString()
        questionerViewModel.setInputData(listInput)
    }

    override fun initView() {
        super.initView()
        val llBottomSheet = binding.bottomSheet.bottomSheet
        val bottomSheetBehavior: BottomSheetBehavior<*> = BottomSheetBehavior.from(llBottomSheet)
        bottomSheetBehavior.addBottomSheetCallback(
            object : BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    val isExpand = bottomSheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED
                    binding.bottomSheet.tvSwipeUp.showViewWithCondition(isShow = !isExpand)
                    binding.bottomSheet.tvTitle.showViewWithCondition(isShow = isExpand)
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {

                }
            }
        )
        itemStrategiKeuanganAdapter = ItemStrategiAdapter()
        itemStrategiKepuasanAdapter = ItemStrategiAdapter()
        itemStrategiPembelajaranAdapter = ItemStrategiAdapter()
        itemStrategiProsesBisnisAdapter = ItemStrategiAdapter()
        itemStrategiSosialAdapter = ItemStrategiAdapter()
        itemStrategiLingkunganAdapter = ItemStrategiAdapter()
        with(binding.bottomSheet) {
            rvStrategiKeuangan.adapter = itemStrategiKeuanganAdapter
            rvStrategiKepuasanPelanggan.adapter = itemStrategiKepuasanAdapter
            rvStrategiPembelajaranDanPertumbuhan.adapter = itemStrategiPembelajaranAdapter
            rvStrategiProsesBisnisInternal.adapter = itemStrategiProsesBisnisAdapter
            rvStrategiSosial.adapter = itemStrategiSosialAdapter
            rvStrategiProsesLingkungan.adapter = itemStrategiLingkunganAdapter
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        goToHome()
    }
}