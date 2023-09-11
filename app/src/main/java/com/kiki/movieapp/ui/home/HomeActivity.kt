package com.kiki.movieapp.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.snackbar.Snackbar
import com.kiki.core.data.Resource
import com.kiki.movieapp.databinding.ActivityHomeBinding
import com.kiki.movieapp.ui.detail.DetailActivity
import com.kiki.movieapp.ui.favorite.FavoriteActivity
import com.kiki.movieapp.ui.search.SearchActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private val viewModel by viewModels<HomeViewModel>()
    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityHomeBinding.inflate(layoutInflater)
    }
    private lateinit var carouselAdapter: CarouselAdapter
    private val popularAdapter by lazy {
        CardAdapter {
            startActivity(
                Intent(this@HomeActivity, DetailActivity::class.java).apply {
                    putExtra("movie", it)
                }
            )
        }
    }
    private val upcomingAdapter by lazy {
        CardAdapter {
            startActivity(
                Intent(this@HomeActivity, DetailActivity::class.java).apply {
                    putExtra("movie", it)
                }
            )
        }
    }
    private val topRatedAdapter by lazy {
        CardAdapter {
            startActivity(
                Intent(this@HomeActivity, DetailActivity::class.java).apply {
                    putExtra("movie", it)
                }
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setUpAction()
        setUpCarousel()
        setUpRecyclerView()
    }

    private fun setUpAction() {
        binding.apply {

            searchBar.setOnClickListener {
                startActivity(
                    Intent(
                        this@HomeActivity,
                        SearchActivity::class.java
                    )
                )
            }

            fab.setOnClickListener {
                startActivity(
                    Intent(
                        this@HomeActivity,
                        FavoriteActivity::class.java
                    )
                )
            }
        }
    }

    private fun setUpCarousel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.nowPlayingMovies.collectLatest { resource ->
                    when (resource) {
                        is Resource.Loading -> {
                            binding.layoutState.isVisible = true
                            binding.progressBar.isVisible = true
                        }

                        is Resource.Success -> {
                            binding.layoutState.isVisible = false
                            carouselAdapter = CarouselAdapter(resource.data ?: emptyList())
                            binding.carousel.adapter = carouselAdapter
                        }

                        is Resource.Error -> showSnackbar(resource.message)
                    }
                }
            }
        }
    }

    private fun setUpRecyclerView() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.popularMovies.collectLatest { resource ->
                    when (resource) {
                        is Resource.Loading -> {
                            binding.layoutState.isVisible = true
                            binding.progressBar.isVisible = true
                        }

                        is Resource.Success -> {
                            binding.layoutState.isVisible = false
                            binding.rvPopular.adapter = popularAdapter
                            popularAdapter.submitList(resource.data)
                        }

                        is Resource.Error -> showSnackbar(resource.message)
                    }
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.upcomingMovies.collectLatest { resource ->
                    when (resource) {
                        is Resource.Loading -> {
                            binding.layoutState.isVisible = true
                            binding.progressBar.isVisible = true
                        }

                        is Resource.Success -> {
                            binding.layoutState.isVisible = false
                            binding.rvUpcoming.adapter = upcomingAdapter
                            upcomingAdapter.submitList(resource.data)
                        }

                        is Resource.Error -> showSnackbar(resource.message)
                    }
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.topRatedMovies.collectLatest { resource ->
                    when (resource) {
                        is Resource.Loading -> {
                            binding.layoutState.isVisible = true
                            binding.progressBar.isVisible = true
                        }

                        is Resource.Success -> {
                            binding.layoutState.isVisible = false
                            binding.rvTopRated.adapter = topRatedAdapter
                            topRatedAdapter.submitList(resource.data)
                        }

                        is Resource.Error -> showSnackbar(resource.message)
                    }
                }
            }
        }
    }

    private fun showSnackbar(message: String?) {
        Snackbar.make(
            binding.root,
            message ?: "Gagal memuat konten",
            Snackbar.LENGTH_LONG
        ).show()
    }
}