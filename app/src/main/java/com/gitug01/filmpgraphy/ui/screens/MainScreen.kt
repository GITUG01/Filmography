package com.gitug01.filmpgraphy.ui.screens

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gitug01.filmpgraphy.R
import com.gitug01.filmpgraphy.data.impl.FilmRepoImpl
import com.gitug01.filmpgraphy.domain.entity.FilmEntity
import com.gitug01.filmpgraphy.domain.repo.FilmRepo
import com.gitug01.filmpgraphy.ui.FilmsAdapter

class MainScreen : Fragment() {

    var recyclerView: RecyclerView? = null
    var recyclerView02: RecyclerView? = null
    var recyclerView03: RecyclerView? = null
    var recyclerView04: RecyclerView? = null
    var filmRepo: FilmRepo = FilmRepoImpl()
    var adapter: FilmsAdapter = FilmsAdapter()
    var adapter02: FilmsAdapter = FilmsAdapter()
    var adapter03: FilmsAdapter = FilmsAdapter()
    var adapter04: FilmsAdapter = FilmsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("@@@", "start")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("@@@", "Create")

        return inflater.inflate(R.layout.fragment_main_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("@@@", "onViewCreated")

        init()
        prepareToWorkWithRecyclerView()
        addFilmsOnMainScreen()

        super.onViewCreated(view, savedInstanceState)
    }


    fun prepareToWorkWithRecyclerView() {
        recyclerView!!.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView02!!.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView03!!.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView04!!.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        recyclerView!!.adapter = adapter
        recyclerView02!!.adapter = adapter02
        recyclerView03!!.adapter = adapter03
        recyclerView04!!.adapter = adapter04

        adapter.setData(filmRepo.films())
        adapter02.setData(filmRepo.filmsTop())
        adapter03.setData(filmRepo.filmsSoon())
        adapter04.setData(filmRepo.filmsNow())
    }

    fun init() {
        recyclerView = view?.findViewById(R.id.main_recycler)
        recyclerView02 = view?.findViewById(R.id.main_recycler2)
        recyclerView03 = view?.findViewById(R.id.main_recycler3)
        recyclerView04 = view?.findViewById(R.id.main_recycler4)
    }

    fun addFilmsOnMainScreen() {
        filmRepo.addFilm(FilmEntity(R.drawable.ic_launcher_background, "Family", "6.8", 2021))
        filmRepo.addFilm(FilmEntity(R.drawable.ic_launcher_background, "Family2", "7.0", 2020))
        filmRepo.addFilm(FilmEntity(R.drawable.ic_launcher_background, "Family3", "7.0", 2020))
        filmRepo.addFilm(FilmEntity(R.drawable.ic_launcher_background, "Family4", "7.0", 2020))
        filmRepo.addFilm(FilmEntity(R.drawable.ic_launcher_background, "Family5", "7.0", 2020))
        filmRepo.addFilm(FilmEntity(R.drawable.ic_launcher_background, "Family6", "7.0", 2020))
        adapter.setData(filmRepo.films())

        filmRepo.addFilmToTop(FilmEntity(R.drawable.ic_launcher_background, "Tor", "6.8", 2021))
        filmRepo.addFilmToTop(FilmEntity(R.drawable.ic_launcher_background, "Tor", "7.0", 2020))
        filmRepo.addFilmToTop(FilmEntity(R.drawable.ic_launcher_background, "Tor", "7.0", 2020))
        filmRepo.addFilmToTop(FilmEntity(R.drawable.ic_launcher_background, "Tor", "7.0", 2020))
        filmRepo.addFilmToTop(FilmEntity(R.drawable.ic_launcher_background, "Tor", "7.0", 2020))
        filmRepo.addFilmToTop(FilmEntity(R.drawable.ic_launcher_background, "Tor", "7.0", 2020))
        adapter02.setData(filmRepo.filmsTop())

        filmRepo.addFilmToNow(FilmEntity(R.drawable.ic_launcher_background, "Nowella", "6.8", 2021))
        filmRepo.addFilmToNow(FilmEntity(R.drawable.ic_launcher_background, "Nowella", "7.0", 2020))
        filmRepo.addFilmToNow(FilmEntity(R.drawable.ic_launcher_background, "Nowella", "7.0", 2020))
        filmRepo.addFilmToNow(FilmEntity(R.drawable.ic_launcher_background, "Nowella", "7.0", 2020))
        filmRepo.addFilmToNow(FilmEntity(R.drawable.ic_launcher_background, "Nowella", "7.0", 2020))
        adapter03.setData(filmRepo.filmsNow())

        filmRepo.addFilmToSoon(FilmEntity(R.drawable.ic_launcher_background, "MySon", "6.8", 2021))
        filmRepo.addFilmToSoon(FilmEntity(R.drawable.ic_launcher_background, "MySon", "7.0", 2020))
        filmRepo.addFilmToSoon(FilmEntity(R.drawable.ic_launcher_background, "MySon", "7.0", 2020))
        filmRepo.addFilmToSoon(FilmEntity(R.drawable.ic_launcher_background, "MySon", "7.0", 2020))
        adapter04.setData(filmRepo.filmsSoon())
    }
}