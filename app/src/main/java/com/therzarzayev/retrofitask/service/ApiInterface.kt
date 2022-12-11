package com.therzarzayev.retrofitask.service

import com.therzarzayev.retrofitask.response.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("3/list/1?")
    fun getMovieDataService(
        @Query("api_key") api_key: String
    ): Call<MovieResponse>
}