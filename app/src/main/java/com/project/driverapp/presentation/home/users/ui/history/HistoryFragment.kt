package com.project.driverapp.presentation.home.users.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.project.driverapp.adapter.RiwayatAdapter
import com.project.driverapp.databinding.FragmentHistoryBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!
    private val historyViewModel: HistoryViewModel by viewModel()
    private lateinit var riwayatAdapter: RiwayatAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        historyViewModel.getRiwayatKuesioner()
        riwayatAdapter = RiwayatAdapter()
        binding.rvHistory.adapter = riwayatAdapter
        historyViewModel.listKuesionerLiveData.observe(viewLifecycleOwner) {
            riwayatAdapter.submitLists(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}