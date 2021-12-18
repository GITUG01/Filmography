package com.gitug01.filmpgraphy.data.retrofit

import android.util.Log
import com.gitug01.filmpgraphy.domain.entity.FilmEntity
import com.gitug01.filmpgraphy.domain.repo.DBFilmRepo
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitDBFilmRepoImpl : DBFilmRepo {

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private var api: DBFApi = retrofit.create(DBFApi::class.java)
    private var result = emptyList<FilmEntity>().toMutableList()

    override fun getFilmsForUserSync(requestCode: String, apiKey: String): List<FilmEntity> {
//        val a = api.getPopularFilms(requestCode, apiKey)
//        val b = a.execute()
//        result = emptyList<FilmEntity>().toMutableList()
//        val c = b.body()
//            ?.results?.forEach {
//
//                result.add(it)
//            }
        return emptyList()
    }

    override suspend fun getFilmsForUserAsync(
        requestCode: String,
        apiKey: String
    ): List<FilmEntity> {
        var result1 = emptyList<FilmEntity>().toMutableList()

        return CoroutineScope(Dispatchers.IO).async {
            val a = api.getPopularFilms(requestCode, apiKey)
            val b = a.execute()
            result = emptyList<FilmEntity>().toMutableList()
            val c = b.body()
                ?.results?.forEach {
                    result.add(it)
                }
            Log.d("films", result.toString())
            return@async result
        }.await()
    }

}