package com.example.evinochallenge.interactor


import com.example.evinochallenge.Constants
import com.example.evinochallenge.entity.ChuckFact
import com.example.evinochallenge.entity.ChuckFacts
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Url

interface ChuckServices {

    @GET()
    fun list(@Url tag: String?): Observable<ChuckFacts>

    @GET(Constants.LIST_ALL_CATEGORIES)
    fun listCategories(): Observable<List<String>>

    @GET()
    fun respostaUnica(@Url tag: String?): Observable<ChuckFact>
}