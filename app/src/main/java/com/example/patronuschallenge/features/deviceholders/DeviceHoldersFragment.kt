package com.example.patronuschallenge.features.deviceholders

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.patronuschallenge.databinding.FragmentDeviceHoldersBinding
import com.example.patronuschallenge.features.base.BaseFragment
import com.example.patronuschallenge.features.deviceholders.adapter.DeviceHolderAdapter
import com.example.patronuschallenge.utils.UiUtils
import org.koin.androidx.viewmodel.ext.android.viewModel

class DeviceHoldersFragment : BaseFragment<FragmentDeviceHoldersBinding>(
    FragmentDeviceHoldersBinding::inflate
) {
    private val viewModel by viewModel<DeviceHoldersViewModelImpl>()

    private val adapter by lazy {
        DeviceHolderAdapter {
            findNavController().navigate(DeviceHoldersFragmentDirections.toDetailsFragment(it.id))
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
        observe()
    }

    private fun setupUi() {
        with(binding) {
            deviceHoldersRecyclerView.adapter = adapter
            deviceHoldersRecyclerView.addItemDecoration(
                UiUtils.getDividerIconDecoration(requireContext())
            )
            swipeRefreshLayout.setOnRefreshListener {
                viewModel.fetchDeviceHolders()
            }
        }
    }

    private fun observe() {
        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect { state ->
                binding.swipeRefreshLayout.isRefreshing = state.loading

                state.deviceHolders?.let {
                    adapter.submitList(it)
                }
                if (!state.error.isNullOrBlank()) {
                    showError(state.error)
                }
            }
        }
    }
}