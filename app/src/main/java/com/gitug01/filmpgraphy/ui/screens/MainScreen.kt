package com.gitug01.filmpgraphy.ui.screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gitug01.filmpgraphy.R
import com.gitug01.filmpgraphy.data.impl.FilmRepoImpl
import com.gitug01.filmpgraphy.domain.entity.FilmEntity
import com.gitug01.filmpgraphy.domain.repo.FilmRepo
import com.gitug01.filmpgraphy.ui.FilmsAdapter

class MainScreen : Fragment() {

    var recyclerView: RecyclerView? = null
    var filmRepo: FilmRepo = FilmRepoImpl()
    var adapter: FilmsAdapter = FilmsAdapter()

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

        recyclerView = view.findViewById(R.id.main_recycler)
        recyclerView!!.layoutManager = LinearLayoutManager(context)
        recyclerView!!.adapter = adapter
        adapter.setData(filmRepo.films())

        filmRepo.addFilm(FilmEntity(R.drawable.ic_launcher_background, "Family", "6.8", 2021))
        filmRepo.addFilm(FilmEntity(R.drawable.ic_launcher_background, "Family2", "7.0", 2020))
        adapter.setData(filmRepo.films())

        super.onViewCreated(view, savedInstanceState)
        Log.d("@@@", "onViewCreated")
    }
}