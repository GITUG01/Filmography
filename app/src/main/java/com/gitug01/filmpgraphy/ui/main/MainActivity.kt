package com.gitug01.filmpgraphy.ui.main

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.view.WindowManager
import android.widget.EditText
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.IdRes
import androidx.annotation.NonNull
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.gitug01.filmpgraphy.R
import com.gitug01.filmpgraphy.data.RoomDb.NoteEntity
import com.gitug01.filmpgraphy.data.RoomDb.NoteRepo
import com.gitug01.filmpgraphy.data.net.apps
import com.gitug01.filmpgraphy.domain.entity.FilmEntity
import com.gitug01.filmpgraphy.domain.repo.DBFilmRepo
import com.gitug01.filmpgraphy.ui.screens.FilmFragment
import com.gitug01.filmpgraphy.ui.screens.MainFragment
import com.yandex.mapkit.MapKitFactory


class MainActivity : AppCompatActivity(), MainFragment.SetDataToTopFilms,
    MainFragment.SetDataToNowFilms,
    MainFragment.SetDataToForYouFilms,
    MainFragment.SetDataToSoonFilms,
    MainFragment.WorkInRoom,
    FilmFragment.FilmFragmentWorkWithRoom,
    FilmFragment.GpsLocation {

    private val API_KEY = "4d8766a8247a32c87963478c66ea350b"

    private val dbFilmRepo: DBFilmRepo by lazy { apps.DBFilmRepo }

    private val REQUEST_CODE_TOP = "popular"
    private val REQUEST_CODE_NOW = "now_playing"
    private val REQUEST_CODE_SOON = "upcoming"
    private val REQUEST_CODE_RATED = "top_rated"

    val KEY_NAME = "name"
    val KEY_RATING = "rating"
    val KEY_YEAR = "year"
    val KEY_IMAGE = "image"
    val KEY_NOTE = "note"
    val DATA_T0_FILM_FRAGMENT = "data_toFilm_fragment"


    private val targetPermission = Manifest.permission.ACCESS_FINE_LOCATION
    private val secondTargetPermission = Manifest.permission.ACCESS_COARSE_LOCATION

    private val permissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {}

    private var editText: EditText? = null
    private val noteRepo: NoteRepo by lazy { apps.noteRepo }

    val l = LOCATION_SERVICE

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MapKitFactory.setApiKey("00e0ef93-5841-4a18-ab84-57d74ce5d841")
        MapKitFactory.initialize(this)

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

    override fun addOrUpdate(filmEntity: FilmEntity, note: String) {
        noteRepo.addOrUpdate(NoteEntity(0, filmEntity.original_title, note))
        val s = noteRepo.getAllFilms()
        val f = noteRepo.get(filmEntity.original_title)
        Log.d("@@@", "$s/n$f")
    }

    override fun exist(filmName: String): Boolean {
        return noteRepo.exist(filmName)
    }

    override fun getNoteEntity(filmEntity: FilmEntity): String {
        return noteRepo.get(filmEntity.original_title)!!.note
    }

    override fun delete(filmEntity: FilmEntity) {
        TODO("Not yet implemented")
    }

    override fun clear() {
        TODO("Not yet implemented")
    }


        @SuppressLint("MissingPermission")
    @RequiresApi(Build.VERSION_CODES.M)
    override fun getLocation(): String {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(arrayOf(targetPermission), 1122)
        }
        requestPermissions(arrayOf(secondTargetPermission), 1122)

        val a = getSystemService(LOCATION_SERVICE) as LocationManager
        val b = a.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        val string = b?.let {
            "[${it.latitude}, ${it.longitude}]"
        }
        return string!!
    }
}