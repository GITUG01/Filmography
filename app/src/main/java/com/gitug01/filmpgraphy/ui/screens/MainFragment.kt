package com.gitug01.filmpgraphy.ui.screens

import android.content.Context
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
import com.gitug01.filmpgraphy.domain.entity.OnLongFilmClickListener
import com.gitug01.filmpgraphy.domain.repo.FilmRepo
import com.gitug01.filmpgraphy.ui.FilmsAdapter

private val REQUEST_CODE_TOP = "popular"
private val REQUEST_CODE_NOW = "now_playing"
private val REQUEST_CODE_SOON = "upcoming"
private val REQUEST_CODE_RATED = "top_rated"

class MainFragment : Fragment(), OnFilmClickListener, OnLongFilmClickListener {


    var recyclerView: RecyclerView? = null
    var recyclerView02: RecyclerView? = null
    var recyclerView03: RecyclerView? = null
    var recyclerView04: RecyclerView? = null
    var filmRepo: FilmRepo = FilmRepoImpl()
    var adapter: FilmsAdapter = FilmsAdapter(this, this)
    var adapter02: FilmsAdapter = FilmsAdapter(this, this)
    var adapter03: FilmsAdapter = FilmsAdapter(this, this)
    var adapter04: FilmsAdapter = FilmsAdapter(this, this)
    private var setDataToTopFilms: SetDataToTopFilms? = null
    private var setDataToNowFilms: SetDataToNowFilms? = null
    private var setDataToForYouFilms: SetDataToForYouFilms? = null
    private var setDataToSoonFilms: SetDataToSoonFilms? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.setDataToNowFilms = context as SetDataToNowFilms
        this.setDataToTopFilms = context as SetDataToTopFilms
        this.setDataToForYouFilms = context as SetDataToForYouFilms
        this.setDataToSoonFilms = context as SetDataToSoonFilms



    }

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
        Thread {

            val r = setDataToTopFilms!!.setDataTop(REQUEST_CODE_TOP)
            val r1 = setDataToNowFilms!!.setDataNow(REQUEST_CODE_NOW)
            val r2 = setDataToForYouFilms!!.setDataForYou(REQUEST_CODE_RATED)
            val r3 = setDataToSoonFilms!!.setDataSoon(REQUEST_CODE_SOON)

            activity?.runOnUiThread {
                adapter02.setData(r)
                adapter03.setData(r1)
                adapter.setData(r2)
                adapter04.setData(r3)
            }

        }.start()

    }


    override fun onItemClicked(filmEntity: FilmEntity) {
        Toast.makeText(context, "Toast", Toast.LENGTH_SHORT).show()

        val filmFragment: FilmFragment = FilmFragment().newInstance(
            filmEntity.poster_path,
            filmEntity.release_date,
            filmEntity.vote_average,
            filmEntity.original_title
        )

        parentFragmentManager.beginTransaction()
            .replace(R.id.fragments_container, filmFragment)
            .addToBackStack(null)
            .commit()
    }


    interface SetDataToTopFilms {
        fun setDataTop(requestCode: String): List<FilmEntity>
    }

    interface SetDataToNowFilms {
        fun setDataNow(requestCode: String): List<FilmEntity>
    }

    interface SetDataToForYouFilms {
        fun setDataForYou(requestCode: String): List<FilmEntity>
    }

    interface SetDataToSoonFilms {
        fun setDataSoon(requestCode: String): List<FilmEntity>
    }

    override fun onLongItemClick(filmEntity: FilmEntity) {
        Toast.makeText(requireContext(), "LongClick", Toast.LENGTH_SHORT).show()
    }
}


