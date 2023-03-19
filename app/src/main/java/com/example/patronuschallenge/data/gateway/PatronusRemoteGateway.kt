package com.example.patronuschallenge.data.gateway

import com.example.patronuschallenge.data.api.PatronusApi
import com.example.patronuschallenge.model.DeviceHolder
import com.example.patronuschallenge.model.DeviceHolderDetails


class PatronusRemoteGateway(private val api: PatronusApi) : PatronusGateway {
    override suspend fun getUsers(): List<DeviceHolder> {
        return api.getUsers().customers
    }

    override suspend fun getUserDetails(id: Int): DeviceHolderDetails {
        return api.getUserDetails(id)
    }
}