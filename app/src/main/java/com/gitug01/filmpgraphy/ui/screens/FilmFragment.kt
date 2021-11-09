package com.gitug01.filmpgraphy.ui.screens

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.VideoView
import com.gitug01.filmpgraphy.R

class FilmFragment : Fragment() {

    private val videoPatch = "android.resource://" + "com.gitug01.filmpgraphy" + "/" + R.raw.film_trailer
    private val uri: Uri = Uri.parse(videoPatch)
    private var video: VideoView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_film, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        video = view.findViewById(R.id.trailer)

        video?.setVideoURI(uri)

        video?.setMediaController(MediaController(context))
        video?.requestFocus(0)
        video?.start()

        super.onViewCreated(view, savedInstanceState)
    }
}