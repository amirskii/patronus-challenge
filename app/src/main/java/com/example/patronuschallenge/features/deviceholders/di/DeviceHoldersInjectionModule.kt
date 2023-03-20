package com.example.patronuschallenge.features.deviceholders.di

import com.example.patronuschallenge.mappers.DeviceHolderPmMapper
import com.example.patronuschallenge.features.deviceholders.DeviceHoldersViewModelImpl
import com.example.patronuschallenge.usecase.FetchDeviceHoldersUseCase
import com.example.patronuschallenge.usecase.FetchDeviceHoldersUseCaseImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object DeviceHoldersInjectionModule {

    val module = module {

        viewModel {
            DeviceHoldersViewModelImpl(
                fetchDeviceHoldersUseCase = get(),
                deviceHolderPmMapper = get()
            )
        }

        factory<FetchDeviceHoldersUseCase> {
            FetchDeviceHoldersUseCaseImpl(get())
        }

        single { DeviceHolderPmMapper(get()) }
    }
}