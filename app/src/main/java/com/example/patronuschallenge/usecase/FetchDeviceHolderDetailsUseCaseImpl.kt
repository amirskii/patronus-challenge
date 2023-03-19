package com.example.patronuschallenge.usecase

import com.example.patronuschallenge.data.gateway.PatronusGateway
import com.example.patronuschallenge.model.CustomerAddress
import com.example.patronuschallenge.model.DeviceHolderDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FetchDeviceHolderDetailsUseCaseImpl(
    private val patronusGateway: PatronusGateway
) : FetchDeviceHolderDetailsUseCase {

    override suspend fun execute(): Flow<DeviceHolderDetails> =
        flow { //            val customer = patronusGateway.getUsers()
            val customer = DeviceHolderDetails(
                id = 1,
                imageUrl = "https://fastly.picsum.photos/id/473/200/300.jpg?hmac=WYG6etF60iOJeGoFVY1hVDMakbBRS32ZDGNkVZhF6-8",
                currentLatitude = 37.7749,
                currentLongitude = -122.4194,
                firstName = "John",
                lastName = "Doe",
                stickers = listOf("Fam"),
                gender = "MALE",
                phoneNumber = "123-456-7890",
                address = CustomerAddress(
                    street = "123 Main St",
                    city = "San Francisco",
                    state = "CA",
                    zip = "94111",
                    country = "USA"
                )
            )
            emit(customer)
        }
}