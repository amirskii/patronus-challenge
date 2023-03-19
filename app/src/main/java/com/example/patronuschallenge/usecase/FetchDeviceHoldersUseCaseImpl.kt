package com.example.patronuschallenge.usecase

import com.example.patronuschallenge.data.gateway.PatronusGateway
import com.example.patronuschallenge.model.DeviceHolder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FetchDeviceHoldersUseCaseImpl(
    private val patronusGateway: PatronusGateway
) : FetchDeviceHoldersUseCase {

    override suspend fun execute(): Flow<List<DeviceHolder>> =
        flow {
            val customers = patronusGateway.getUsers()
            emit(customers)
        }
}