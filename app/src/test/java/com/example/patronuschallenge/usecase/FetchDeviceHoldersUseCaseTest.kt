package com.example.patronuschallenge.usecase

import com.example.patronuschallenge.base.BaseViewModelTest
import com.example.patronuschallenge.data.gateway.PatronusGateway
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Test

class FetchDeviceHoldersUseCaseTest : BaseViewModelTest() {

    @MockK
    private lateinit var patronusGateway: PatronusGateway

    @Test
    fun `should call gateway`() =
        runTest {
            val interactor = initWithMocks {
                coEvery { patronusGateway.getUsers() } returns mockk()
            }

            interactor.execute()
                .toList()

            coVerifySequence {
                patronusGateway.getUsers()
            }
        }

    private fun initWithMocks(mockBlock: () -> Unit): FetchDeviceHoldersUseCaseImpl {
        mockBlock()
        return FetchDeviceHoldersUseCaseImpl(
            patronusGateway
        )
    }
}