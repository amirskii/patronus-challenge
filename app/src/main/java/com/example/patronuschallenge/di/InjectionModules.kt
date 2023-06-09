package com.example.patronuschallenge.di

import com.example.patronuschallenge.data.di.NetworkInjectionModule
import com.example.patronuschallenge.features.details.di.DetailsInjectionModule
import com.example.patronuschallenge.features.deviceholders.di.DeviceHoldersInjectionModule


object InjectionModules {

    val modules = listOf(
        DeviceHoldersInjectionModule.module,
        NetworkInjectionModule.module,
        DetailsInjectionModule.module
    )
}