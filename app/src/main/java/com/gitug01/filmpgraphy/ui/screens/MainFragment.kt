package com.gitug01.filmpgraphy.ui.screens

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gitug01.filmpgraphy.R
import com.gitug01.filmpgraphy.data.impl.FilmRepoImpl
import com.gitug01.filmpgraphy.domain.entity.FilmEntity
import com.gitug01.filmpgraphy.domain.entity.OnFilmClickListener
import com.gitug01.filmpgraphy.domain.repo.FilmRepo
import com.gitug01.filmpgraphy.ui.FilmsAdapter
import com.gitug01.filmpgraphy.ui.main.MainActivity

class MainFragment : Fragment(), OnFilmClickListener {


    var recyclerView: RecyclerView? = null
    var recyclerView02: RecyclerView? = null
    var recyclerView03: RecyclerView? = null
    var recyclerView04: RecyclerView? = null
    var filmRepo: FilmRepo = FilmRepoImpl()
    var adapter: FilmsAdapter = FilmsAdapter(this)
    var adapter02: FilmsAdapter = FilmsAdapter(this)
    var adapter03: FilmsAdapter = FilmsAdapter(this)
    var adapter04: FilmsAdapter = FilmsAdapter(this)

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


    fun init() {
        recyclerView = view?.findViewById(R.id.main_recycler)
        recyclerView02 = view?.findViewById(R.id.main_recycler2)
        recyclerView03 = view?.findViewById(R.id.main_recycler3)
        recyclerView04 = view?.findViewById(R.id.main_recycler4)
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


        recyclerView?.let { it.adapter = adapter }
        recyclerView02?.let { it.adapter = adapter02 }
        recyclerView03?.let { it.adapter = adapter03 }
        recyclerView04?.let { it.adapter = adapter04 }

        adapter.setData(filmRepo.films())
        adapter02.setData(filmRepo.filmsTop())
        adapter03.setData(filmRepo.filmsSoon())
        adapter04.setData(filmRepo.filmsNow())
    }

    fun addFilmsOnMainScreen() {
        filmRepo.addFilm(FilmEntity(R.drawable.image2, "Family", 6.8, 2021))
        filmRepo.addFilm(FilmEntity(R.drawable.image, "Family2", 4.0, 2020))
        filmRepo.addFilm(FilmEntity(R.drawable.image2, "Family3", 7.0, 2020))
        filmRepo.addFilm(FilmEntity(R.drawable.ic_launcher_background, "Family4", 7.0, 2020))
        filmRepo.addFilm(FilmEntity(R.drawable.ic_launcher_background, "Family5", 7.0, 2020))
        filmRepo.addFilm(FilmEntity(R.drawable.ic_launcher_background, "Family6", 7.0, 2020))
        adapter.setData(filmRepo.films())

        filmRepo.addFilmToTop(FilmEntity(R.drawable.image4, "Tor", 5.4, 2021))
        filmRepo.addFilmToTop(FilmEntity(R.drawable.image5, "Tor", 10.0, 2020))
        filmRepo.addFilmToTop(FilmEntity(R.drawable.ic_launcher_background, "Tor", 7.0, 2020))
        filmRepo.addFilmToTop(FilmEntity(R.drawable.ic_launcher_background, "Tor", 7.0, 2020))
        filmRepo.addFilmToTop(FilmEntity(R.drawable.ic_launcher_background, "Tor", 7.0, 2020))
        filmRepo.addFilmToTop(FilmEntity(R.drawable.ic_launcher_background, "Tor", 7.0, 2020))
        adapter02.setData(filmRepo.filmsTop())

        filmRepo.addFilmToNow(FilmEntity(R.drawable.ic_launcher_background, "Nowella", 6.9, 2021))
        filmRepo.addFilmToNow(FilmEntity(R.drawable.ic_launcher_background, "Nowella", 7.0, 2020))
        filmRepo.addFilmToNow(FilmEntity(R.drawable.ic_launcher_background, "Nowella", 7.0, 2020))
        filmRepo.addFilmToNow(FilmEntity(R.drawable.ic_launcher_background, "Nowella", 7.0, 2020))
        filmRepo.addFilmToNow(FilmEntity(R.drawable.ic_launcher_background, "Nowella", 7.0, 2020))
        adapter03.setData(filmRepo.filmsNow())

        filmRepo.addFilmToSoon(FilmEntity(R.drawable.ic_launcher_background, "MySon", 6.8, 2021))
        filmRepo.addFilmToSoon(FilmEntity(R.drawable.ic_launcher_background, "MySon", 7.0, 2020))
        filmRepo.addFilmToSoon(FilmEntity(R.drawable.ic_launcher_background, "MySon", 7.0, 2020))
        filmRepo.addFilmToSoon(FilmEntity(R.drawable.ic_launcher_background, "MySon", 7.0, 2020))
        adapter04.setData(filmRepo.filmsSoon())
    }

    override fun onItemClicked(noteEntity: FilmEntity) {
        Toast.makeText(context, noteEntity.image.toString(), Toast.LENGTH_SHORT).show()

        val filmFragment: FilmFragment = FilmFragment().newInstance(noteEntity.image!!, noteEntity.year!!, noteEntity.rating!!, noteEntity.name!!)

        parentFragmentManager.beginTransaction()
            .replace(R.id.fragments_container, filmFragment)
            .addToBackStack(null)
            .commit()
    }

}