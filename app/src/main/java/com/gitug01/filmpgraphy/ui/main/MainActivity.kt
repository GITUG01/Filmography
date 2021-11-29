package com.gitug01.filmpgraphy.ui.main

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.gitug01.filmpgraphy.R
import com.gitug01.filmpgraphy.data.net.apps
import com.gitug01.filmpgraphy.domain.entity.FilmEntity
import com.gitug01.filmpgraphy.domain.entity.OnFilmClickListener
import com.gitug01.filmpgraphy.domain.repo.DBFilmRepo
import com.gitug01.filmpgraphy.ui.screens.MainFragment
import android.os.VibrationEffect

import android.os.Build

import android.os.Vibrator
import com.gitug01.filmpgraphy.data.RoomDb.NoteEntity
import com.gitug01.filmpgraphy.data.RoomDb.NoteRepo


class MainActivity : AppCompatActivity(), MainFragment.SetDataToTopFilms,
    MainFragment.SetDataToNowFilms,
    MainFragment.SetDataToForYouFilms,
    MainFragment.SetDataToSoonFilms
{

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

        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            vibrator.vibrate(500)
        }
    }

    override fun setDataTop(requestCode: String): List<FilmEntity> {
        return dbFilmRepo.getFilmsForUserSync(requestCode, API_KEY)
    }

    override fun setDataForYou(requestCode: String): List<FilmEntity> {
        return dbFilmRepo.getFilmsForUserSync(requestCode, API_KEY)
    }

    override fun setDataNow(requestCode: String): List<FilmEntity> {
        return dbFilmRepo.getFilmsForUserSync(requestCode, API_KEY)
    }

    override fun setDataSoon(requestCode: String): List<FilmEntity> {
        return dbFilmRepo.getFilmsForUserSync(requestCode, API_KEY)
    }

}