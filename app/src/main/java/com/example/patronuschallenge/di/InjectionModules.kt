package com.example.patronuschallenge.di

import com.example.patronuschallenge.data.di.NetworkInjectionModule
import com.example.patronuschallenge.ui.details.di.DetailsInjectionModule
import com.example.patronuschallenge.ui.deviceholders.di.DeviceHoldersInjectionModule


object InjectionModules {

    val modules = listOf(
        DeviceHoldersInjectionModule.module,
        NetworkInjectionModule.module,
        DetailsInjectionModule.module
    )
}