package com.gitug01.filmpgraphy.ui

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.gitug01.filmpgraphy.R

class FilmViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var filmCardView: CardView = itemView.findViewById(R.id.film_container)
    var image: ImageView = itemView.findViewById(R.id.image)
    var name: TextView = itemView.findViewById(R.id.name)
    var year: TextView = itemView.findViewById(R.id.year)
    var rating: TextView = itemView.findViewById(R.id.rating)

}