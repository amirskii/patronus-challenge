package com.example.patronuschallenge.usecase

import com.example.patronuschallenge.model.DeviceHolder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FetchDeviceHoldersUseCaseImpl(
    //    private val profileGateway: ProfileGateway
) : FetchDeviceHoldersUseCase {

    override suspend fun execute(): Flow<List<DeviceHolder>> =
        flowOf(
            listOf(
                DeviceHolder(
                    id = 1,
                    firstName = "John",
                    lastName = "Doe",
                    gender = "MALE",
                    phoneNumber = "123-456-7890",
                    imageUrl = "https://fastly.picsum.photos/id/473/200/300.jpg?hmac=WYG6etF60iOJeGoFVY1hVDMakbBRS32ZDGNkVZhF6-8",
                    stickers = listOf("Fam")
                )
            )
        )

}