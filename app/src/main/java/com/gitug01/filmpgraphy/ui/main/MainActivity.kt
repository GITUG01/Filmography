package com.gitug01.filmpgraphy.ui.main

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import androidx.annotation.IdRes
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.gitug01.filmpgraphy.R
import com.gitug01.filmpgraphy.data.net.apps
import com.gitug01.filmpgraphy.domain.entity.FilmEntity
import com.gitug01.filmpgraphy.domain.repo.DBFilmRepo
import com.gitug01.filmpgraphy.ui.screens.MainFragment

class MainActivity : AppCompatActivity(), View.OnTouchListener, MainFragment.SetDataToTopFilms,
    MainFragment.SetDataToNowFilms,
    MainFragment.SetDataToForYouFilms,
    MainFragment.SetDataToSoonFilms {

    private val API_KEY = "4d8766a8247a32c87963478c66ea350b"

    private val dbFilmRepo: DBFilmRepo by lazy { apps.DBFilmRepo }

    private val REQUEST_CODE_TOP = "popular"
    private val REQUEST_CODE_NOW = "now_playing"
    private val REQUEST_CODE_SOON = "upcoming"
    private val REQUEST_CODE_RATED = "top_rated"

    val KEY_NAME = "NAME"
    val KEY_RATING = "rating"
    val KEY_YEAR = "year"
    val KEY_IMAGE = "image"
    val DATA_T0_FILM_FRAGMENT = "data_toFilm_fragment"

    private var editText: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        Thread {
//
//            val films = dbFilmRepo.getFilmsForUserSync(REQUEST_CODE_TOP)
//
//            val sb = StringBuilder()
//            films.forEach {
//                sb.appendLine(it.toString())
//
//                runOnUiThread { Log.d("@@@", "sb.length.toString())") }
//
//            }
//
//        }.start()


        editText?.findViewById<EditText>(R.id.title_edit_text)

        replaceFragment(R.id.fragments_container, MainFragment(), false)

        this.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }

    private fun replaceFragment(
        @IdRes containerViewId: Int,
        @NonNull fragment: Fragment,
        addToBackStack: Boolean
    ) {
        when (addToBackStack) {
            false -> supportFragmentManager.beginTransaction().replace(containerViewId, fragment)
                .commit()
            true -> supportFragmentManager.beginTransaction().replace(containerViewId, fragment)
                .addToBackStack(null).commit()
        }
    }

    override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
        TODO("Not yet implemented")
    }

    override fun setDataTop(): List<FilmEntity> {
        return dbFilmRepo.getFilmsForUserSync(REQUEST_CODE_TOP, API_KEY)
    }

    override fun setDataNow(): List<FilmEntity> {
        return dbFilmRepo.getFilmsForUserSync(REQUEST_CODE_NOW, API_KEY)
    }

    override fun setDataForYou(): List<FilmEntity> {
        return dbFilmRepo.getFilmsForUserSync(REQUEST_CODE_RATED, API_KEY)
    }

    override fun setDataSoon(): List<FilmEntity> {
        return dbFilmRepo.getFilmsForUserSync(REQUEST_CODE_SOON, API_KEY)
    }

}