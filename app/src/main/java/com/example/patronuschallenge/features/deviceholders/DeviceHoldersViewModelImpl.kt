package com.example.patronuschallenge.features.deviceholders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.patronuschallenge.mappers.DeviceHolderPmMapper
import com.example.patronuschallenge.usecase.FetchDeviceHoldersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DeviceHoldersViewModelImpl(
    private val fetchDeviceHoldersUseCase: FetchDeviceHoldersUseCase,
    private val deviceHolderPmMapper: DeviceHolderPmMapper
) : DeviceHoldersViewModel, ViewModel() {

    override val uiState = MutableStateFlow(DeviceHoldersUiState())

    init {
        fetchDeviceHolders()
    }


    override fun fetchDeviceHolders() {
        uiState.update { state ->
            state.copy(
                loading = true
            )
        }
        viewModelScope.launch {
            fetchDeviceHoldersUseCase.execute()
                .catch {
                    uiState.update { state -> state.copy(error = it.localizedMessage) }
                }
                .onEach { deviceHolders ->
                    uiState.update { state ->
                        state.copy(
                            deviceHolders = deviceHolderPmMapper.mapList(deviceHolders),
                            loading = false,
                            error = null
                        )
                    }
                }
                .collect()
        }
    }

}