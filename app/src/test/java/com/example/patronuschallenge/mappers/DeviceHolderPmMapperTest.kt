package com.example.patronuschallenge.mappers

import android.content.Context
import com.example.patronuschallenge.R
import com.example.patronuschallenge.base.BaseTest
import com.example.patronuschallenge.features.model.DeviceHolderPm
import com.example.patronuschallenge.model.DeviceHolder
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Test

class DeviceHolderPmMapperTest : BaseTest() {

    @MockK
    private lateinit var context: Context

    private lateinit var mapper: DeviceHolderPmMapper
    override fun setup() {
        super.setup()
        mapper = DeviceHolderPmMapper(context)

        every { context.getString(R.string.fam) } returns "Fam"
        every { context.getString(R.string.ban) } returns "Ban"
    }

    @Test
    fun `should map domain device holder model to presentation model`() {
        mapper.map(customer)
            .shouldBe(customerPm)
    }

    private val customerPm = DeviceHolderPm(
        id = 1,
        imageUrl = "imageUrl",
        name = "John Doe",
        gender = "Male",
        phoneNumber = "123-456-7890",
        imagePlaceholder = "JD",
        showFam = true,
        showBan = false,
    )

    private val customer = DeviceHolder(
        id = 1,
        imageUrl = "imageUrl",
        firstName = "John",
        lastName = "Doe",
        stickers = listOf("Fam"),
        gender = "MALE",
        phoneNumber = "123-456-7890",
    )
}
