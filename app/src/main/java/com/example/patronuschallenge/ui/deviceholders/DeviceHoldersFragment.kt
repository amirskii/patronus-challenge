package com.example.patronuschallenge.ui.deviceholders

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.patronuschallenge.R
import com.example.patronuschallenge.databinding.FragmentDeviceHoldersBinding
import com.example.patronuschallenge.ui.base.BaseFragment
import com.example.patronuschallenge.ui.deviceholders.adapter.DeviceHolderAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class DeviceHoldersFragment : BaseFragment<FragmentDeviceHoldersBinding>(
    FragmentDeviceHoldersBinding::inflate
) {
    private val viewModel by viewModel<DeviceHoldersViewModelImpl>()

    private val adapter by lazy {
        DeviceHolderAdapter {
            findNavController().navigate(R.id.to_DetailsFragment)
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
            deviceHoldersRecyclerView.addItemDecoration( // TODO: custom drawable
                DividerItemDecoration(
                    requireContext(), LinearLayoutManager.VERTICAL
                )
            )
        }
    }

    private fun observe() {
        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect { state ->
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