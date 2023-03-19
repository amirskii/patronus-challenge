package com.example.patronuschallenge.data.gateway

import com.example.patronuschallenge.model.DeviceHolder
import kotlinx.coroutines.flow.Flow

interface PatronusGateway {
    suspend fun getUsers(): List<DeviceHolder>
}