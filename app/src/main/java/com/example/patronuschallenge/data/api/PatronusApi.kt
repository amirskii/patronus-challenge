package com.example.patronuschallenge.data.api

import com.example.patronuschallenge.data.model.GetUsersResponse
import com.example.patronuschallenge.model.DeviceHolderDetails
import retrofit2.http.GET
import retrofit2.http.Path

interface PatronusApi {
    @GET("users")
    suspend fun getUsers(): GetUsersResponse

    @GET("users/{id}")
    suspend fun getUserDetails(
        @Path("id") id: Int
    ): DeviceHolderDetails
}