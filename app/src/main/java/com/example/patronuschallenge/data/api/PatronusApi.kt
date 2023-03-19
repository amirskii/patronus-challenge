package com.example.patronuschallenge.data.api

import com.example.patronuschallenge.data.model.GetUsersResponse
import retrofit2.http.GET

interface PatronusApi {
    @GET("users")
    suspend fun getUsers(): GetUsersResponse
}