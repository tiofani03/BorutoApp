package com.tiooooo.borutoapp.data.remote.api

import com.tiooooo.borutoapp.data.remote.response.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BorutoApi {

    @GET("/boruto/getAllHeroes")
    suspend fun getAllHeroes(
        @Query("page") page: Int = 1,
    ): ApiResponse

    @GET("/boruto/getAllHeroes/search")
    suspend fun searchHeroes(
        @Query("name") name: String,
    ): ApiResponse
}
