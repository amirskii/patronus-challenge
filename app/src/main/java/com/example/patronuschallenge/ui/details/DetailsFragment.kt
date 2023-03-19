package com.example.patronuschallenge.ui.details

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.example.patronuschallenge.databinding.FragmentDetailsBinding
import com.example.patronuschallenge.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment : BaseFragment<FragmentDetailsBinding>(FragmentDetailsBinding::inflate) {

    private val viewModel by viewModel<DetailsViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
        observe()
    }

    private fun setupUi() {
        with(binding) {}
    }

    private fun observe() {
        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect { state ->
                state.deviceHolder?.let {}
                if (!state.error.isNullOrBlank()) {
                    showError(state.error)
                }
            }
        }
    }
}