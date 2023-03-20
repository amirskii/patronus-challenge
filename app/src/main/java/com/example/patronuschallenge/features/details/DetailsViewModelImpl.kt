package com.example.patronuschallenge.features.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.patronuschallenge.mappers.DeviceHolderDetailsPmMapper
import com.example.patronuschallenge.usecase.FetchDeviceHolderDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailsViewModelImpl(
    customerId: Int,
    private val fetchDeviceHolderDetailsUseCase: FetchDeviceHolderDetailsUseCase,
    private val detailsPmMapper: DeviceHolderDetailsPmMapper
) : DeviceHoldersViewModel, ViewModel() {

    override val uiState = MutableStateFlow(DetailsUiState())

    init {
        fetchDeviceHolders(customerId)
    }


    private fun fetchDeviceHolders(customerId: Int) {
        uiState.update { state ->
            state.copy(
                loading = true
            )
        }
        viewModelScope.launch {
            fetchDeviceHolderDetailsUseCase.execute(customerId)
                .catch {
                    uiState.update { state -> state.copy(error = it.localizedMessage) }
                }
                .onEach { deviceHolder ->
                    uiState.update { state ->
                        state.copy(
                            deviceHolder = detailsPmMapper.map(deviceHolder),
                            loading = false,
                            error = null
                        )
                    }
                }
                .collect()
        }
    }

}