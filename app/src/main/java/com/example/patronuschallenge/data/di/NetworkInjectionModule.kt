package com.example.patronuschallenge.data.di

import com.example.patronuschallenge.data.api.PatronusApi
import com.example.patronuschallenge.data.gateway.PatronusGateway
import com.example.patronuschallenge.data.gateway.PatronusRemoteGateway
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkInjectionModule {

    val module = module {

        factory<PatronusGateway> { PatronusRemoteGateway(get()) }

        factory<GsonConverterFactory> {
            GsonConverterFactory.create()
        }

        factory<OkHttpClient> {
            OkHttpClient.Builder()
                .build()
        }

        single<Retrofit> {
            Retrofit.Builder()
                .baseUrl("https://api.code-challenge.patronus-group.com/")
                .client(get<OkHttpClient>())
                .addConverterFactory(get<GsonConverterFactory>())
                .build()
        }

        single<PatronusApi> { providePatronusApiService(get()) }
    }

    private fun providePatronusApiService(retrofit: Retrofit) =
        retrofit.create(PatronusApi::class.java)
}