package com.example.patronuschallenge.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule

@ExperimentalCoroutinesApi
abstract class BaseViewModelTest : BaseTest() {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = MainCoroutineRule()
}