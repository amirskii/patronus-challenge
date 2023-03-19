package com.example.patronuschallenge.data.gateway

import com.example.patronuschallenge.model.DeviceHolder
import com.example.patronuschallenge.model.DeviceHolderDetails

interface PatronusGateway {
    suspend fun getUsers(): List<DeviceHolder>

    suspend fun getUserDetails(id: Int): DeviceHolderDetails
}