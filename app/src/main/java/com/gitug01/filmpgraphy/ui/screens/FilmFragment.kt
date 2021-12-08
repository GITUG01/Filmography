package com.gitug01.filmpgraphy.ui.screens

import android.Manifest
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.Display
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.gitug01.filmpgraphy.R
import com.gitug01.filmpgraphy.domain.entity.FilmEntity
import com.gitug01.filmpgraphy.ui.main.MainActivity
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.mapview.MapView

private const val CHECKBOX_KEY = "checkbox_key"

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
    private var checkBox: CheckBox? = null
    private val targetPermission = Manifest.permission.ACCESS_FINE_LOCATION
    private var linearLayout: LinearLayout? = null
    var mapView: MapView? = null
    private val preferences: SharedPreferences by lazy { requireActivity().getPreferences(0) }

    fun newInstance(
        image: String,
        year: String,
        rating: String,
        name: String,
        note: String
    ): FilmFragment {
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

        MapKitFactory.setApiKey("00e0ef93-5841-4a18-ab84-57d74ce5d841")
        MapKitFactory.initialize(context)


        return inflater.inflate(R.layout.fragment_film, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requestPermissions(arrayOf(targetPermission), 1122)

        init(view)
        assignmentValues()
        showOrHideMap()


//        mapView?.map?.move(
//            CameraPosition(Point(55.751574, 37.573856), 11.0f, 0.0f, 0.0f),
//            Animation(Animation.Type.SMOOTH, 0f),
//            null
//        )

        super.onViewCreated(view, savedInstanceState)
    }

    private fun init(view: View) {
        mapView = view.findViewById(R.id.mapview)
        actors = view.findViewById(R.id.actors)
        title = view.findViewById(R.id.title_description)
        yearDescription = view.findViewById(R.id.year_description)
        description = view.findViewById(R.id.description)
        picture = view.findViewById(R.id.fragment_film_image)
        noteEt = view.findViewById(R.id.note_et)
        checkBox = view.findViewById(R.id.checkbox_show_mapp)
        linearLayout = view.findViewById(R.id.main_layout)

    }

    fun assignmentValues() {
        title?.text = arguments?.getString(MainActivity().KEY_NAME).toString()
        Glide
            .with(this)
            .load("https://image.tmdb.org/t/p/w185/" + (arguments?.getString(MainActivity().KEY_IMAGE)))
            .into(picture!!)
        yearDescription?.text = arguments?.getString(MainActivity().KEY_YEAR).toString()
        description?.text = "Hello, World!!!"
        actors?.text =
            "Тимоти Шаламе, Ребекка Фергюсон, Оскар Айзек, Джош Бролин, Стеллан Скарсгард, " +
                    "Дейв Батиста, Стивен Маккинли Хендерсон, Зендея, Чан Чэньruen, Шарлотта Рэмплинг, Джейсон Момоа, Хавьер Бардем"
        noteEt?.setText(arguments?.getString(MainActivity().KEY_NOTE))
    }

    fun showOrHideMap() {

        checkBox?.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                if (checkingPermission()) mapView?.visibility = View.VISIBLE
            } else mapView?.visibility = View.INVISIBLE
        }
    }


    private fun checkingPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            targetPermission
        ) == PermissionChecker.PERMISSION_GRANTED
    }

    private fun checkingCheckbox() {
        when (checkBox?.isChecked) {
            true -> mapView?.visibility = View.VISIBLE
            false -> mapView?.visibility = View.INVISIBLE
        }
    }

    interface FilmFragmentWorkWithRoom {
        fun addOrUpdate(filmEntity: FilmEntity, note: String)
    }


    override fun onStart() {
        super.onStart()
        mapView?.onStart()
        checkingCheckbox()

        MapKitFactory.getInstance().onStart()

        checkBox!!.isChecked = preferences.getBoolean(CHECKBOX_KEY, false)
    }

    override fun onStop() {
        super.onStop()
        mapView?.onStop()
        MapKitFactory.getInstance().onStop()

        preferences.edit().let {
            it.putBoolean(CHECKBOX_KEY, checkBox!!.isChecked)
            it.commit()
        }
    }
}