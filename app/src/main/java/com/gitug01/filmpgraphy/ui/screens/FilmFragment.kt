package com.gitug01.filmpgraphy.ui.screens

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.gitug01.filmpgraphy.R
import com.gitug01.filmpgraphy.data.RoomDb.NoteEntity
import com.gitug01.filmpgraphy.domain.entity.FilmEntity
import com.gitug01.filmpgraphy.ui.main.MainActivity

class FilmFragment : Fragment() {

    private val videoPatch =
        "android.resource://" + "com.gitug01.filmpgraphy" + "/" + R.raw.film_trailer
    private val uri: Uri = Uri.parse(videoPatch)
    private var video: VideoView? = null


    private var title: TextView? = null
    private var yearDescription: TextView? = null
    private var description: TextView? = null
    private var actors: TextView? = null
    private var picture: ImageView? = null
    private var noteEt: EditText? = null

    fun newInstance(image: String, year: String, rating: String, name: String, note: String): FilmFragment {
        val f = FilmFragment()
        val args = Bundle()
        args.putString(MainActivity().KEY_IMAGE, image)
        args.putString(MainActivity().KEY_YEAR, year)
        args.putString(MainActivity().KEY_RATING, rating)
        args.putString(MainActivity().KEY_NAME, name)
        args.putString(MainActivity().KEY_NOTE, note)
        f.arguments = args
        f.arguments
        return f
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_film, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        init(view)




        title?.text = arguments?.getString(MainActivity().KEY_NAME).toString()
        Glide
            .with(this)
            .load("https://image.tmdb.org/t/p/w185/"+ (arguments?.getString(MainActivity().KEY_IMAGE)))
            .into(picture!!)
        yearDescription?.text = arguments?.getString(MainActivity().KEY_YEAR).toString()
        description?.text = "Hello, World!!!"
        actors?.text = "Тимоти Шаламе, Ребекка Фергюсон, Оскар Айзек, Джош Бролин, Стеллан Скарсгард, " +
                "Дейв Батиста, Стивен Маккинли Хендерсон, Зендея, Чан Чэньruen, Шарлотта Рэмплинг, Джейсон Момоа, Хавьер Бардем"
        noteEt?.setText(arguments?.getString(MainActivity().KEY_NOTE))


        super.onViewCreated(view, savedInstanceState)
    }
    private fun init(view: View){
        actors = view.findViewById(R.id.actors)
        title = view.findViewById(R.id.title_description)
        yearDescription = view.findViewById(R.id.year_description)
        description = view.findViewById(R.id.description)
        picture = view.findViewById(R.id.fragment_film_image)
        noteEt = view.findViewById(R.id.note_et)
    }

}