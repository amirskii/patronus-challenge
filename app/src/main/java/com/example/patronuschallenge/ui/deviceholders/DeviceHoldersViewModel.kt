package com.example.patronuschallenge.ui.deviceholders

import com.example.patronuschallenge.ui.model.DeviceHolderPm
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

interface DeviceHoldersViewModel {
    val uiState: Flow<DeviceHoldersUiState>
}

data class DeviceHoldersUiState(
    val deviceHolders: List<DeviceHolderPm>? = null,
    val loading: Boolean = false,
    val error: String? = null
)

