package com.example.patronuschallenge.mappers

import android.content.Context
import com.example.patronuschallenge.R
import com.example.patronuschallenge.base.BaseTest
import com.example.patronuschallenge.features.model.DeviceHolderDetailsPm
import com.example.patronuschallenge.model.CustomerAddress
import com.example.patronuschallenge.model.DeviceHolderDetails
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Test

class DeviceHolderDetailsPmMapperTest : BaseTest() {

    @MockK
    private lateinit var context: Context

    private lateinit var mapper: DeviceHolderDetailsPmMapper
    override fun setup() {
        super.setup()
        mapper = DeviceHolderDetailsPmMapper(context)

        every { context.getString(R.string.fam) } returns "Fam"
        every { context.getString(R.string.ban) } returns "Ban"
    }

    @Test
    fun `should map domain device holder model to presentation model`() {
        mapper.map(customer)
            .shouldBe(customerPm)
    }

    private val customerPm = DeviceHolderDetailsPm(
        id = 1,
        imageUrl = "imageUrl",
        lat = 37.7749,
        lon = -122.4194,
        name = "John Doe",
        gender = "Male",
        phoneNumber = "123-456-7890",
        address = "123 Main St, 94111 San Francisco",
        imagePlaceholder = "JD",
        showFam = true,
        showBan = false,
    )

    private val customer = DeviceHolderDetails(
        id = 1,
        imageUrl = "imageUrl",
        currentLatitude = 37.7749,
        currentLongitude = -122.4194,
        firstName = "John",
        lastName = "Doe",
        stickers = listOf("Fam"),
        gender = "MALE",
        phoneNumber = "123-456-7890",
        address = CustomerAddress(
            street = "123 Main St",
            city = "San Francisco",
            state = "CA",
            zip = "94111",
            country = "USA"
        )
    )
}
