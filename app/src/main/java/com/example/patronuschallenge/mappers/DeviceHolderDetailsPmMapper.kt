package com.example.patronuschallenge.mappers

import android.content.Context
import com.example.patronuschallenge.R
import com.example.patronuschallenge.model.DeviceHolderDetails
import com.example.patronuschallenge.ui.model.DeviceHolderDetailsPm

class DeviceHolderDetailsPmMapper(
    private val context: Context
) {

    fun map(input: DeviceHolderDetails): DeviceHolderDetailsPm {
        val placeholder = "${input.firstName.first()}${input.lastName.first()}"

        return DeviceHolderDetailsPm(
            id = input.id,
            name = "${input.firstName} ${input.lastName}",
            gender = input.gender.lowercase().replaceFirstChar { it.uppercase() },
            phoneNumber = input.phoneNumber,
            imageUrl = input.imageUrl ?: "",
            imagePlaceholder = placeholder,
            showFam = input.stickers.contains(context.getString(R.string.fam)),
            showBan = input.stickers.contains(context.getString(R.string.ban)),
            address = "${input.address.street}, ${input.address.zip} ${input.address.city}",
            lat = input.currentLatitude,
            lon = input.currentLongitude
        )
    }
}