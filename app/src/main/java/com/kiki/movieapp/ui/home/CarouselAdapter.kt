package com.kiki.movieapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.github.islamkhsh.CardSliderAdapter
import com.kiki.core.domain.model.Movie
import com.kiki.movieapp.databinding.CardCarouselBinding

class CarouselAdapter(private val movies: List<Movie>) :
    CardSliderAdapter<CarouselAdapter.ViewHolder>() {
    override fun bindVH(holder: ViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount() = movies.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CardCarouselBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    class ViewHolder(private val binding: CardCarouselBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.poster.load("https://image.tmdb.org/t/p/w500${movie.backdropUrl}") {
                crossfade(500)
                scale(Scale.FILL)
            }
            binding.tvTitle.text = movie.title
        }
    }

}