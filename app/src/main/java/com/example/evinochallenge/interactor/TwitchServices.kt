package com.example.evinochallenge.interactor


import com.example.evinochallenge.entity.TopGames
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers

interface TwitchServices {
    @Headers(
        "Accept: application/vnd.twitchtv.v5+json",
        "Client-ID: f8rsf1kd4ets0g8mvyrvu60i9ofvzw"
    )
    @GET("top/")
    fun list(): Observable<TopGames>
}