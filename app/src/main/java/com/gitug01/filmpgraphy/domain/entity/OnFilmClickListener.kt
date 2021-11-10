package com.gitug01.filmpgraphy.domain.entity

import android.content.Intent
import com.gitug01.filmpgraphy.ui.screens.FilmFragment
import com.gitug01.filmpgraphy.ui.screens.MainScreen

interface OnFilmClickListener {
    fun onItemClicked(noteEntity: FilmEntity)
}