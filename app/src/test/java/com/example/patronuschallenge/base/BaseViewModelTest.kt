package com.example.patronuschallenge.base

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule

@ExperimentalCoroutinesApi
abstract class BaseViewModelTest : BaseTest() {

    @get:Rule
    val coroutineRule = MainCoroutineRule()
}