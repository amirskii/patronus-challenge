package com.example.patronuschallenge.features.deviceholders

import com.example.patronuschallenge.base.BaseViewModelTest
import com.example.patronuschallenge.features.model.DeviceHolderDetailsPm
import com.example.patronuschallenge.features.model.DeviceHolderPm
import com.example.patronuschallenge.mappers.DeviceHolderPmMapper
import com.example.patronuschallenge.model.DeviceHolder
import com.example.patronuschallenge.usecase.FetchDeviceHoldersUseCase
import io.kotest.matchers.shouldBe
import io.mockk.coVerifySequence
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DeviceHoldersViewModelTest : BaseViewModelTest() {

    private val fetchDeviceHoldersUseCase = FakeFetchDeviceHoldersUseCase()

    @MockK
    lateinit var deviceHolderPmMapper: DeviceHolderPmMapper

    private lateinit var viewModel: DeviceHoldersViewModelImpl

    private fun buildViewModel(): DeviceHoldersViewModelImpl {
        return DeviceHoldersViewModelImpl(
            fetchDeviceHoldersUseCase,
            deviceHolderPmMapper
        )
    }

    override fun setup() {
        super.setup()
        viewModel = buildViewModel()
    }

    @Test
    fun `should call use case and mapper sequentially`() =
        runTest {
            setupMocks(listOf(customerPm))

            fetchDeviceHoldersUseCase.emit(listOf(customer))

            coVerifySequence {
                fetchDeviceHoldersUseCase.execute()
                deviceHolderPmMapper.mapList(any())
            }
        }

        @Test
        fun `on init should change state from loading`() =
            runTest {
                val results = mutableListOf<DeviceHoldersUiState>()
                setupMocks(listOf(customerPm))

                backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
                    viewModel.uiState.toList(results)
                }
                fetchDeviceHoldersUseCase.emit(listOf(customer))

                results.first().shouldBe(DeviceHoldersUiState().copy(loading = true))
            }

    @Test
    fun `on init should change state to success`() =
        runTest {
            val results = mutableListOf<DeviceHoldersUiState>()
            setupMocks(listOf(customerPm))

            backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
                viewModel.uiState.toList(results)
            }
            fetchDeviceHoldersUseCase.emit(listOf(customer))

            results.last().shouldBe(DeviceHoldersUiState().copy(deviceHolders = listOf(customerPm)))
        }

    internal class FakeFetchDeviceHoldersUseCase : FetchDeviceHoldersUseCase {
        private val flow = MutableSharedFlow<List<DeviceHolder>>()
        suspend fun emit(value: List<DeviceHolder>) = flow.emit(value)
        override suspend fun execute(): Flow<List<DeviceHolder>> = flow
    }

    private fun setupMocks(
        result: List<DeviceHolderPm>
    ) {
        every {
            deviceHolderPmMapper.mapList(any())
        } returns result
    }
    
    private val customerPm = DeviceHolderPm(
        id = 1,
        imageUrl = "imageUrl",
        name = "John Doe",
        gender = "Male",
        phoneNumber = "123-456-7890",
        imagePlaceholder = "JD",
        showFam = true,
        showBan = true,
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