package com.kiki.movieapp.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.kiki.core.domain.model.Movie
import com.kiki.movieapp.databinding.CardFavoriteBinding
import com.kiki.movieapp.util.MovieComparator

class FavoriteAdapter(private val onCLick: (Movie) -> Unit) :
    ListAdapter<Movie, FavoriteAdapter.ViewHolder>(MovieComparator) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteAdapter.ViewHolder {
        return ViewHolder(
            CardFavoriteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FavoriteAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: CardFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.apply {
                imgPoster.load("https://image.tmdb.org/t/p/w500${movie.posterUrl}") {
                    crossfade(500)
                    scale(Scale.FILL)
                }
                tvRating.text = "${movie.rating}/10 (${movie.ratingCount})"
                tvAge.text = if (movie.adult == true) "All" else "Adult"
                tvReleaseDate.text = movie.releaseDate
                tvTitle.text = movie.title
            }

            itemView.setOnClickListener { onCLick(movie) }
        }
    }
}