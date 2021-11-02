package com.gitug01.filmpgraphy.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gitug01.filmpgraphy.R
import com.gitug01.filmpgraphy.domain.entity.FilmEntity

class FilmsAdapter : RecyclerView.Adapter<FilmViewHolder>() {
    private var data: List<FilmEntity>? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<FilmEntity>){
        this.data = ArrayList(data)
        notifyDataSetChanged()
    }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
            var view: View = LayoutInflater.from(parent.context).inflate(R.layout.film, parent, false)
            return FilmViewHolder(view)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        var film: FilmEntity = getItem(position)
        holder.name.text = film.name
        holder.year.text = film.year.toString()
        holder.rating.text = film.rating
    }

    private fun getItem(position: Int) = data!![position]

    override fun getItemCount(): Int {
        return data!!.size
    }
}