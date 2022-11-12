package com.ftp.keberlanjutanumkmbsc.presentation.onboarding

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.ftp.keberlanjutanumkmbsc.adapter.OnBoardingAdapter
import com.ftp.keberlanjutanumkmbsc.databinding.ActivityOnBoardingBinding
import com.ftp.keberlanjutanumkmbsc.domain.model.getOnBoarding
import com.ftp.keberlanjutanumkmbsc.utils.goToLanding
import com.ftp.keberlanjutanumkmbsc.utils.helper.SnapOnScrollListener
import com.ftp.keberlanjutanumkmbsc.utils.helper.SnapOnScrollListener.Companion.NOTIFY_ON_SCROLL

class OnBoardingActivity : AppCompatActivity() {

    private var _binding: ActivityOnBoardingBinding? = null
    private val binding get() = _binding!!
    private lateinit var onBoardingAdapter: OnBoardingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initOnBoarding()
        initClick()
    }

    private fun initClick() {
        with(binding) {
            tvMulai.setOnClickListener {
                goToLanding()
            }
            tvLewati.setOnClickListener {
                goToLanding()
            }
        }
    }

    private fun initOnBoarding() {
        val listOnBoardingModel = getOnBoarding()
        val helper: SnapHelper = LinearSnapHelper()
        helper.attachToRecyclerView(binding.rvOnboarding)
        onBoardingAdapter = OnBoardingAdapter()
        onBoardingAdapter.submitList(listOnBoardingModel)
        with(binding) {
            rvOnboarding.adapter = onBoardingAdapter
            tvNext.setOnClickListener {
                val layoutManager = rvOnboarding.layoutManager as LinearLayoutManager
                val firstVisiblePosition = layoutManager.findFirstVisibleItemPosition()
                if (firstVisiblePosition == listOnBoardingModel.size - 2) {
                    rvOnboarding.smoothScrollToPosition(firstVisiblePosition + 1)
                    tvNext.visibility = View.GONE
                    tvMulai.visibility = View.VISIBLE
                } else {
                    rvOnboarding.smoothScrollToPosition(firstVisiblePosition + 1)
                    tvNext.visibility = View.VISIBLE
                    tvMulai.visibility = View.GONE
                }
            }
            recyclerViewIndicator.setRecyclerView(rvOnboarding)

            rvOnboarding.addOnScrollListener(
                SnapOnScrollListener(
                    helper,
                    NOTIFY_ON_SCROLL
                ) { position ->
                    if (position == 2) {
                        tvMulai.visibility = View.VISIBLE
                        tvNext.visibility = View.GONE
                    } else {
                        tvMulai.visibility = View.GONE
                        tvNext.visibility = View.VISIBLE
                    }
                })
        }
    }

}