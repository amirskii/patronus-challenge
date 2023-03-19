package com.example.patronuschallenge.data.model

import com.example.patronuschallenge.model.DeviceHolder

data class GetUsersResponse(
    var customers: List<DeviceHolder> = listOf(),
)