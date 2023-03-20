package com.example.patronuschallenge.features.details.di

import com.example.patronuschallenge.mappers.DeviceHolderDetailsPmMapper
import com.example.patronuschallenge.features.details.DetailsViewModelImpl
import com.example.patronuschallenge.usecase.FetchDeviceHolderDetailsUseCase
import com.example.patronuschallenge.usecase.FetchDeviceHolderDetailsUseCaseImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object DetailsInjectionModule {

    val module = module {

        viewModel { (customerId: Int) ->
            DetailsViewModelImpl(
                customerId = customerId,
                fetchDeviceHolderDetailsUseCase = get(),
                detailsPmMapper = get()
            )
        }

        factory<FetchDeviceHolderDetailsUseCase> {
            FetchDeviceHolderDetailsUseCaseImpl(get())
        }

        single { DeviceHolderDetailsPmMapper(get()) }
    }
}