package com.gitug01.filmpgraphy.data.net

import android.util.Log
import com.gitug01.filmpgraphy.data.net.entity.O
import com.gitug01.filmpgraphy.domain.entity.FilmEntity
import com.gitug01.filmpgraphy.domain.repo.DBFilmRepo
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class WebDBFilmRepoImpl : DBFilmRepo {
    override fun getFilmsForUserSync(requestCode: String): List<FilmEntity> {

        val gson by lazy { Gson() }

        val result = emptyList<FilmEntity>().toMutableList()

        var connection: HttpURLConnection? = null
        try {
            connection = getUrl(requestCode).openConnection() as HttpURLConnection
            connection.requestMethod = "GET"

            val bufReader = BufferedReader(InputStreamReader(connection.inputStream))
            val resultJsonString = bufReader.readLines().toString()

            val reposArray = gson.fromJson(
                resultJsonString, Array<O>::class.java
            )

            reposArray?.forEach { it ->
                it.results.forEach {
                    result.add(it)
                }
            }
        } catch (ex: Exception) {
            Log.d("@@@", ex.printStackTrace().toString())
        } finally {
            connection?.disconnect()
        }

        return result
    }

    private fun getUrl(requestCode: String) =
        URL("https://api.themoviedb.org/3/$requestCode&api_key=4d8766a8247a32c87963478c66ea350b")
}