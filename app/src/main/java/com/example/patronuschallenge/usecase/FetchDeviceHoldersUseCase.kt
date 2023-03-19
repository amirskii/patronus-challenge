package com.example.patronuschallenge.usecase

import com.example.patronuschallenge.model.DeviceHolder
import kotlinx.coroutines.flow.Flow

interface FetchDeviceHoldersUseCase {

    suspend fun execute(): Flow<List<DeviceHolder>>
}