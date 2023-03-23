package com.example.patronuschallenge.features.details

import com.example.patronuschallenge.base.BaseViewModelTest
import com.example.patronuschallenge.features.model.DeviceHolderDetailsPm
import com.example.patronuschallenge.mappers.DeviceHolderDetailsPmMapper
import com.example.patronuschallenge.model.CustomerAddress
import com.example.patronuschallenge.model.DeviceHolderDetails
import com.example.patronuschallenge.usecase.FetchDeviceHolderDetailsUseCase
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
class DetailsViewModelTest : BaseViewModelTest() {

    private val fetchDeviceHolderDetailsUseCase = FakeFetchDeviceHolderDetailsUseCase()

    @MockK
    lateinit var detailsPmMapper: DeviceHolderDetailsPmMapper

    private lateinit var viewModel: DetailsViewModelImpl

    private val customerId = 1

    private fun buildViewModel(): DetailsViewModelImpl {
        return DetailsViewModelImpl(
            customerId,
            fetchDeviceHolderDetailsUseCase,
            detailsPmMapper
        )
    }

    override fun setup() {
        super.setup()
        viewModel = buildViewModel()
    }

    @Test
    fun `should call use case and mapper sequentially`() =
        runTest {
            setupMocks(customerDetailsPm)

            fetchDeviceHolderDetailsUseCase.emit(customer)

            coVerifySequence {
                fetchDeviceHolderDetailsUseCase.execute(customerId)
                detailsPmMapper.map(any())
            }
        }

    @Test
    fun `on init should change state from loading`() =
        runTest {
            val results = mutableListOf<DetailsUiState>()
            setupMocks(customerDetailsPm)

            // in this case flow is infinite toList won't work
            // collecting coroutine that will continuously receive the values from use case
            backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
                viewModel.uiState.toList(results)
            }
            fetchDeviceHolderDetailsUseCase.emit(customer)

            results.first()
                .shouldBe(DetailsUiState().copy(loading = true))
        }

    @Test
    fun `on init should change state to success`() =
        runTest {
            val results = mutableListOf<DetailsUiState>()
            setupMocks(customerDetailsPm)

            backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
                viewModel.uiState.toList(results)
            }
            fetchDeviceHolderDetailsUseCase.emit(customer)

            results.last()
                .shouldBe(DetailsUiState().copy(deviceHolder = customerDetailsPm))
        }

    internal class FakeFetchDeviceHolderDetailsUseCase : FetchDeviceHolderDetailsUseCase {
        private val flow = MutableSharedFlow<DeviceHolderDetails>()
        suspend fun emit(value: DeviceHolderDetails) =
            flow.emit(value)

        override suspend fun execute(id: Int): Flow<DeviceHolderDetails> =
            flow
    }

    private fun setupMocks(
        result: DeviceHolderDetailsPm
    ) {
        every {
            detailsPmMapper.map(any())
        } returns result
    }

    private val customerDetailsPm = DeviceHolderDetailsPm(
        id = 1,
        imageUrl = "imageUrl",
        lat = 37.7749,
        lon = -122.4194,
        name = "John Doe",
        gender = "Male",
        phoneNumber = "123-456-7890",
        address = "123 Main St",
        imagePlaceholder = "JD",
        showFam = true,
        showBan = true,
    )

    private val customer = DeviceHolderDetails(
        id = 1,
        imageUrl = "https://fastly.picsum.photos/id/473/200/300.jpg?hmac=WYG6etF60iOJeGoFVY1hVDMakbBRS32ZDGNkVZhF6-8",
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