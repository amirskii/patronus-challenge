package com.example.patronuschallenge.features.model

data class DeviceHolderDetailsPm(
    val id: Int,
    val name: String,
    val gender: String,
    val phoneNumber: String,
    val imageUrl: String,
    val imagePlaceholder: String,
    val address: String,
    val showFam: Boolean = false,
    val showBan: Boolean = false,
    val lat: Double,
    val lon: Double
)
