package com.example.evinochallenge.interactor

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit

import retrofit2.converter.jackson.JacksonConverterFactory

class RetrofitConfig {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.twitch.tv/kraken/games/")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(JacksonConverterFactory.create())
        .build()

    fun buildingService(): TwitchServices {
        return retrofit.create(TwitchServices::class.java)
    }
}