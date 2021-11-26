package com.gitug01.filmpgraphy.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toBitmap
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gitug01.filmpgraphy.R
import com.gitug01.filmpgraphy.domain.entity.FilmEntity
import com.gitug01.filmpgraphy.domain.entity.OnFilmClickListener
import com.gitug01.filmpgraphy.domain.entity.OnLongFilmClickListener

class FilmsAdapter (onFilmClickListener: OnFilmClickListener, onLongFilmClickListener: OnLongFilmClickListener): RecyclerView.Adapter<FilmViewHolder>() {
    private var data: List<FilmEntity>? = null
    private var onFilmClickListener: OnFilmClickListener = onFilmClickListener
    private var onLongFilmClickListener: OnLongFilmClickListener = onLongFilmClickListener

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<FilmEntity>) {
        this.data = ArrayList(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.film, parent, false)
        return FilmViewHolder(view)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        val film: FilmEntity = getItem(position)
        holder.name.text = film.original_title
        holder.year.text = film.release_date
        holder.rating.text = film.vote_average
        Glide.with(holder.image).load("https://image.tmdb.org/t/p/w185/"+film.poster_path).into(holder.image)

        holder.filmCardView.setOnClickListener { onFilmClickListener.onItemClicked(getItem(position)) }
        holder.filmCardView.setOnLongClickListener {
            onLongFilmClickListener.onLongItemClick(getItem(position))
            true
        }
    }

private fun getItem(position: Int) = data!![position]

override fun getItemCount(): Int {
    return data!!.size
}
}