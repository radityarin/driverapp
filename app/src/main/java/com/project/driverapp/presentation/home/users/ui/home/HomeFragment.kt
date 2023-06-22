package com.project.driverapp.presentation.home.users.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.project.driverapp.R
import com.project.driverapp.adapter.ItemToDoAdapter
import com.project.driverapp.data.logic.ToDo
import com.project.driverapp.data.logic.ToDoStatus
import com.project.driverapp.databinding.FragmentHomeBinding
import com.project.driverapp.utils.goToSubmitActivity
import com.project.driverapp.utils.showAcceptJobDialog
import com.project.driverapp.utils.showCustomToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var itemToDoAdapter: ItemToDoAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initClickListeners()
        initRecyclerView()
        initData()
    }

    private fun initData() {
        itemToDoAdapter.submitList(
            listOf(
                ToDo(
                    id = 1,
                    from = "Jakarta",
                    destination = "Malang",
                    startDate = "17-03-2023 10:00 AM",
                    endDate = "17-03-2023 23:00 PM",
                    status = ToDoStatus.ACTIVE
                ),
                ToDo(
                    id = 2,
                    from = "Surabaya",
                    destination = "Cirebon",
                    startDate = "17-03-2023 10:00 AM",
                    endDate = "17-03-2023 23:00 PM",
                    status = ToDoStatus.FINISH
                ),
                ToDo(
                    id = 3,
                    from = "Bekasi",
                    destination = "Pemalang",
                    startDate = "17-03-2023 10:00 AM",
                    endDate = "17-03-2023 17:00 PM",
                    status = ToDoStatus.NON_ACTIVE
                ),
                ToDo(
                    id = 4,
                    from = "Bandung",
                    destination = "Purwakarta",
                    startDate = "17-03-2023 10:00 AM",
                    endDate = "17-03-2023 13:00 PM",
                    status = ToDoStatus.NON_ACTIVE
                )
            )
        )
    }

    private fun initRecyclerView() {
        with(binding) {
            itemToDoAdapter = ItemToDoAdapter(
                onItemClick = {
                    when (it.status) {
                        ToDoStatus.ACTIVE -> {
                            showAcceptJobDialog()
                            itemToDoAdapter.updateStatus(it.id, ToDoStatus.ONGOING)
                        }
                        ToDoStatus.ONGOING -> {
                            activity?.goToSubmitActivity()
                        }
                        else -> {
                            showCustomToast(getString(R.string.finish_job))
                        }
                    }
                }
            )
            rvToDoList.adapter = itemToDoAdapter
        }
    }

    private fun initClickListeners() {
        with(binding) {

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}