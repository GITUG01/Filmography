package com.gitug01.filmpgraphy.data.net.entity

import com.gitug01.filmpgraphy.domain.entity.FilmEntity

data class O (
    var page: Int,
    var results: Array<FilmEntity>,
    var total_pages: Int,
    var total_results: Int
        )