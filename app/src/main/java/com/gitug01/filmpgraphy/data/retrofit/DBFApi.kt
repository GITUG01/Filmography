package com.gitug01.filmpgraphy.data.retrofit

import com.gitug01.filmpgraphy.data.net.entity.O
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DBFApi {

    @GET("/3/movie/{category}")
    fun getPopularFilms(
        @Path("category") category: String,
        @Query("api_key") requestCode: String): Call<O>

}