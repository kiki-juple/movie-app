package com.kiki.movieapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.kiki.core.domain.model.Movie
import com.kiki.movieapp.databinding.CardMovieBinding
import com.kiki.movieapp.util.MovieComparator

class CardAdapter(val onClick: (Movie?) -> Unit) :
    ListAdapter<Movie, CardAdapter.ViewHolder>(MovieComparator) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardAdapter.ViewHolder {
        return ViewHolder(
            CardMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CardAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: CardMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.apply {
                imgMovie.load("https://image.tmdb.org/t/p/w500${movie.posterUrl}") {
                    crossfade(500)
                    scale(Scale.FILL)
                }
                tvRating.text = "${movie.rating}/10"
                tvRatingCount.text = "${movie.ratingCount} rating"
                tvTitle.text = movie.title
            }

            itemView.setOnClickListener { onClick(movie) }
        }
    }
}