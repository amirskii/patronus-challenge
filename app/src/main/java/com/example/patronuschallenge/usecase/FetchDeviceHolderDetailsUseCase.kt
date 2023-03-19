package com.example.patronuschallenge.usecase

import com.example.patronuschallenge.model.DeviceHolderDetails
import kotlinx.coroutines.flow.Flow

interface FetchDeviceHolderDetailsUseCase {

    suspend fun execute(id: Int): Flow<DeviceHolderDetails>
}