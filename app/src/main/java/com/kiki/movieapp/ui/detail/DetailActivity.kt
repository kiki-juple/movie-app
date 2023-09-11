package com.kiki.movieapp.ui.detail

import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import coil.load
import coil.size.Scale
import com.google.android.material.snackbar.Snackbar
import com.kiki.core.data.Resource
import com.kiki.core.domain.model.Movie
import com.kiki.movieapp.R
import com.kiki.movieapp.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityDetailBinding.inflate(layoutInflater)
    }
    private val viewModel by viewModels<DetailViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val movie = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("movie", Movie::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("movie")
        }

        if (movie != null) {
            movie.isFavorite?.let { setFavorite(it) }
            var favorite = movie.isFavorite ?: false
            val message = if (!favorite) {
                "Added to favorite."
            } else {
                "Removed from favorite."
            }
            binding.btnFavorite.setOnClickListener {
                Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
                favorite = !favorite
                viewModel.updateFavorite(movie, favorite)
                setFavorite(favorite)
            }
        }

        getMovieDetail(movie)

    }

    private fun getMovieDetail(movie: Movie?) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.getMovieDetail(movie?.id ?: 0).collectLatest { resource ->
                    when (resource) {
                        is Resource.Loading -> {
                            binding.layoutState.isVisible = true
                            binding.progressBar.isVisible = true
                        }

                        is Resource.Success -> {
                            binding.layoutState.isVisible = false
                            val data = resource.data
                            if (data != null && movie != null) {
                                binding.apply {
                                    imgBackdrop.load("https://image.tmdb.org/t/p/w500${data.backdropUrl}") {
                                        crossfade(500)
                                        scale(Scale.FILL)
                                    }
                                    tvTitle.text = movie.title
                                    tvDesc.text = movie.overView
                                    tvRating.text = "${movie.rating}/10"
                                    tvRatingCount.text = "${movie.ratingCount} rating"
                                    tvYear.text = data.releaseDate
                                }
                            }
                        }

                        is Resource.Error -> {
                            binding.layoutState.isVisible = true
                            binding.progressBar.isVisible = false
                            binding.tvError.text = resource.message
                        }
                    }
                }
            }
        }
    }

    private fun setFavorite(isFavorite: Boolean) {
        if (isFavorite) {
            binding.btnFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_baseline_favorite_24
                )
            )
        } else {
            binding.btnFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_baseline_favorite_border_24
                )
            )
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}