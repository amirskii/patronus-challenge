package com.example.patronuschallenge.features.model

data class DeviceHolderPm(
    val id: Int,
    val name: String,
    val gender: String,
    val phoneNumber: String,
    val imageUrl: String,
    val imagePlaceholder: String,
    val showFam: Boolean = false,
    val showBan: Boolean = false,
)
