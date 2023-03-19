package com.example.patronuschallenge.ui.details

import com.example.patronuschallenge.ui.model.DeviceHolderDetailsPm
import kotlinx.coroutines.flow.Flow

interface DeviceHoldersViewModel {
    val uiState: Flow<DetailsUiState>
}

data class DetailsUiState(
    val deviceHolder: DeviceHolderDetailsPm? = null,
    val loading: Boolean = false,
    val error: String? = null
)

