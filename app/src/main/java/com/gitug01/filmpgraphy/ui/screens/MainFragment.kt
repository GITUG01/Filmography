package com.gitug01.filmpgraphy.ui.screens

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.LinkedBlockingQueue


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
    private var workInRoom: WorkInRoom? = null
    private var list: List<FilmEntity>? = null
    private var list1: List<FilmEntity>? = null
    private var list2: List<FilmEntity>? = null
    private var list3: List<FilmEntity>? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.setDataToNowFilms = context as SetDataToNowFilms
        this.setDataToTopFilms = context as SetDataToTopFilms
        this.setDataToForYouFilms = context as SetDataToForYouFilms
        this.setDataToSoonFilms = context as SetDataToSoonFilms
        this.workInRoom = context as WorkInRoom


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
//        checkBox = view?.findViewById(R.id.checkbox_show_mapp)
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

        CoroutineScope(Dispatchers.IO).launch {
            val r = setDataToTopFilms!!.setDataTop(REQUEST_CODE_TOP)
            val r3 = setDataToSoonFilms!!.setDataSoon(REQUEST_CODE_SOON)
            val r1 = setDataToNowFilms!!.setDataNow(REQUEST_CODE_NOW)
            val r2 = setDataToForYouFilms!!.setDataForYou(REQUEST_CODE_RATED)

            withContext(Dispatchers.Main) {
                adapter02.setData(r)
                adapter03.setData(r1)
                adapter.setData(r2)
                adapter04.setData(r3)
            }
        }

    }


    override fun onItemClicked(filmEntity: FilmEntity) {
        Toast.makeText(context, "Toast", Toast.LENGTH_SHORT).show()
        val queue = LinkedBlockingQueue<String>()
        Thread {
            if (workInRoom?.exist(filmEntity.original_title)!!) {
                queue.put(workInRoom?.getNoteEntity(filmEntity))
            } else {
                queue.put("Your note...")
            }
        }.start()

        val filmFragment: FilmFragment = FilmFragment().newInstance(
            filmEntity.poster_path,
            filmEntity.release_date,
            filmEntity.vote_average,
            filmEntity.original_title,
            queue.take()
        )

        parentFragmentManager.beginTransaction()
            .replace(R.id.fragments_container, filmFragment)
            .addToBackStack(null)
            .commit()
    }


    interface SetDataToTopFilms {
        suspend fun setDataTop(requestCode: String): List<FilmEntity>
    }

    interface SetDataToNowFilms {
        suspend fun setDataNow(requestCode: String): List<FilmEntity>
    }

    interface SetDataToForYouFilms {
        suspend fun setDataForYou(requestCode: String): List<FilmEntity>
    }

    interface SetDataToSoonFilms {
        suspend fun setDataSoon(requestCode: String): List<FilmEntity>
    }

    override fun onLongItemClick(filmEntity: FilmEntity) {
        Toast.makeText(requireContext(), "LongClick", Toast.LENGTH_SHORT).show()
        showAlertDialog(filmEntity)
    }

    fun showAlertDialog(filmEntity: FilmEntity) {
        val inputEditTextField = EditText(requireActivity())
        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("Title")
            .setMessage("Message")
            .setView(inputEditTextField)
            .setPositiveButton("OK") { _, _ ->
                val note = inputEditTextField.text.toString()
                Thread {
                    workInRoom?.addOrUpdate(filmEntity, note)
                }.start()
                Log.d("@@@", "editext value is: $note")
            }
            .setNegativeButton("Cancel", null)
            .create()
        dialog.show()
    }

    interface WorkInRoom {
        fun addOrUpdate(filmEntity: FilmEntity, note: String)
        fun exist(filmName: String): Boolean
        fun getNoteEntity(filmEntity: FilmEntity): String
        fun delete(filmEntity: FilmEntity)
        fun clear()
    }
}


