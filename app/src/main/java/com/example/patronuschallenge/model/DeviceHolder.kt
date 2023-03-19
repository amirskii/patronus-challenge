package com.example.patronuschallenge.model

data class DeviceHolder(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val gender: String,
    val phoneNumber: String,
    val imageUrl: String?,
    val stickers: List<String>
)
