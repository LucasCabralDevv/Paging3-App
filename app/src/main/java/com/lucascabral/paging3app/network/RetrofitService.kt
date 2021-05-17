package com.lucascabral.paging3app.network

import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("character")
    suspend fun getDataFromApi(@Query("page") query: Int): RickAndMortyList
}