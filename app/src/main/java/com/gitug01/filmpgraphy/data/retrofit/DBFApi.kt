package com.gitug01.filmpgraphy.data.retrofit

import com.gitug01.filmpgraphy.data.net.entity.O
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DBFApi {

    @GET("discover/movie?sort_by=popularity.desc")
    fun getPopularFilms(@Query("api_key") apiKey: String): Call<O>

}