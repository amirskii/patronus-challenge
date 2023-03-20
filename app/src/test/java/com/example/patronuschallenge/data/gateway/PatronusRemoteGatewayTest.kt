package com.example.patronuschallenge.data.gateway

import com.example.patronuschallenge.base.BaseViewModelTest
import com.example.patronuschallenge.data.api.PatronusApi
import com.example.patronuschallenge.data.gateway.PatronusGateway
import com.example.patronuschallenge.model.DeviceHolder
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Test

class PatronusRemoteGatewayTest : BaseViewModelTest() {

    @MockK
    private lateinit var api: PatronusApi

    @Test
    fun `getUserDetails should call api`() =
        runTest {
            val gateway = initWithMocks {
                coEvery { api.getUserDetails(any()) } returns
                        mockk()
            }

            gateway.getUserDetails(1)

            coVerifySequence {
                api.getUserDetails(1)
            }
        }

    @Test
    fun `getUsers should call api`() =
        runTest {
            val gateway = initWithMocks {
                coEvery { api.getUsers().customers } returns
                        listOf()
            }

            gateway.getUsers()

            coVerifySequence {
                api.getUsers()
            }
        }

    @Test
    fun `getUsers should return list of DeviceHolders`() =
        runTest {
            val gateway = initWithMocks {
                coEvery { api.getUsers().customers } returns
                        listOf(customer)
            }

            val customers = gateway.getUsers()

            customers.shouldBe(listOf(customer))
        }

    private fun initWithMocks(mockBlock: () -> Unit): PatronusRemoteGateway {
        mockBlock()
        return PatronusRemoteGateway(
            api
        )
    }

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