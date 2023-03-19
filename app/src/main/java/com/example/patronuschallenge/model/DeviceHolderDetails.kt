package com.example.patronuschallenge.model

data class DeviceHolderDetails(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val gender: String,
    val phoneNumber: String,
    val imageUrl: String?,
    val currentLatitude: Double,
    val currentLongitude: Double,
    val address: CustomerAddress,
    val stickers: List<String>
)
