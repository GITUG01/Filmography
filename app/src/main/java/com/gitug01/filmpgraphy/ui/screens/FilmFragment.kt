package com.gitug01.filmpgraphy.ui.screens

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.MediaController
import android.widget.TextView
import android.widget.VideoView
import androidx.fragment.app.Fragment
import com.gitug01.filmpgraphy.R
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

    fun newInstance(image: Int, year: Int, rating: Double, name: String): FilmFragment {
        val f = FilmFragment()
        val args = Bundle()
        args.putInt(MainActivity().KEY_IMAGE, image)
        args.putInt(MainActivity().KEY_YEAR, year)
        args.putDouble(MainActivity().KEY_RATING, rating)
        args.putString(MainActivity().KEY_NAME, name)
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

        actors = view.findViewById(R.id.actors)
        title = view.findViewById(R.id.title_description)
        yearDescription = view.findViewById(R.id.year_description)
        description = view.findViewById(R.id.description)
        picture = view.findViewById(R.id.image)



        title?.text = arguments?.getString(MainActivity().KEY_NAME).toString()
        yearDescription?.text = arguments?.getInt(MainActivity().KEY_YEAR).toString()
        description?.text = "Hello, World!!!"
        actors?.text = "Тимоти Шаламе, Ребекка Фергюсон, Оскар Айзек, Джош Бролин, Стеллан Скарсгард, " +
                "Дейв Батиста, Стивен Маккинли Хендерсон, Зендея, Чан Чэньruen, Шарлотта Рэмплинг, Джейсон Момоа, Хавьер Бардем"
        picture?.setImageResource(arguments?.getInt(MainActivity().KEY_IMAGE) as Int)

        video = view.findViewById(R.id.trailer)

        video?.setVideoURI(uri)
        video?.setMediaController(MediaController(context))
        video?.requestFocus(0)

        super.onViewCreated(view, savedInstanceState)
    }
}