package com.example.patronuschallenge.ui.model

data class DeviceHolderDetailsPm(
    val id: Int,
    val name: String,
    val gender: String,
    val phoneNumber: String,
    val imageUrl: String,
    val imagePlaceholder: String,
    val showFam: Boolean = false,
    val showBan: Boolean = false,
)
