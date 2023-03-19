package com.example.patronuschallenge.mappers

import android.content.Context
import com.example.patronuschallenge.R
import com.example.patronuschallenge.model.DeviceHolder
import com.example.patronuschallenge.ui.model.DeviceHolderPm

class DeviceHolderPmMapper(
    private val context: Context
) {

    fun map(input: DeviceHolder): DeviceHolderPm {
        val placeholder = "${input.firstName.first()} ${input.lastName.first()}"
            .takeIf { input.imageUrl == null } ?: ""

        return DeviceHolderPm(
            id = input.id,
            name = "${input.firstName} ${input.lastName}",
            gender = input.gender.lowercase().replaceFirstChar { it.uppercase() },
            phoneNumber = input.phoneNumber,
            imageUrl = input.imageUrl ?: "",
            imagePlaceholder = placeholder,
            showFam = input.stickers.contains(context.getString(R.string.fam)),
            showBan = input.stickers.contains(context.getString(R.string.ban))
        )
    }

    fun mapList(inputs: List<DeviceHolder>): List<DeviceHolderPm> =
        inputs.map {
            map(it)
        }
}