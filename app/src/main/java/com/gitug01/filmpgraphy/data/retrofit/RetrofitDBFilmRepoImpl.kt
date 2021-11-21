package com.gitug01.filmpgraphy.data.retrofit

import com.gitug01.filmpgraphy.domain.entity.FilmEntity
import com.gitug01.filmpgraphy.domain.repo.DBFilmRepo
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
        result = emptyList<FilmEntity>().toMutableList()
        val a = api.getPopularFilms(requestCode, apiKey)
        val b = a.execute()
        val c = b.body()
            ?.results?.forEach {
                result.add(it)
            }
        return result
    }
}