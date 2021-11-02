package com.gitug01.filmpgraphy.domain.entity

import android.media.Image

class FilmEntity(image: Int, name: String, rating: String, year: Int){

    var id: Int? = null
    var image: Int? = null
    var name: String? = null
    var rating: String? = null
    var year: Int? = null

    init {
        this.image = image
        this.name = name
        this.rating = rating
        this.year = year
    }


}