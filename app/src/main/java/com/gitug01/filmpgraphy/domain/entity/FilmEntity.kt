package com.gitug01.filmpgraphy.domain.entity

class FilmEntity(image: Int, name: String, rating: Double, year: Int) {

    var id: Int? = null
    var image: Int? = null
    var name: String? = null
    var rating: Double? = null
    var year: Int? = null

    init {
        this.image = image
        this.name = name
        this.rating = rating
        this.year = year
    }


}