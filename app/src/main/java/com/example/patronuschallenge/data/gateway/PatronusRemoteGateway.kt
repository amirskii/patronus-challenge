package com.example.patronuschallenge.data.gateway

import com.example.patronuschallenge.data.api.PatronusApi
import com.example.patronuschallenge.model.DeviceHolder


class PatronusRemoteGateway(private val api: PatronusApi): PatronusGateway {
    override suspend fun getUsers(): List<DeviceHolder> {
        return api.getUsers().customers
    }
}