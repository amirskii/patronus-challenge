package com.example.patronuschallenge.features.deviceholders

import com.example.patronuschallenge.features.model.DeviceHolderPm
import kotlinx.coroutines.flow.Flow

interface DeviceHoldersViewModel {
    val uiState: Flow<DeviceHoldersUiState>
    fun fetchDeviceHolders()
}

data class DeviceHoldersUiState(
    val deviceHolders: List<DeviceHolderPm>? = null,
    val loading: Boolean = false,
    val error: String? = null
)

