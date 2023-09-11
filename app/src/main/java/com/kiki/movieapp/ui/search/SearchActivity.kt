package com.kiki.movieapp.ui.search

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.kiki.core.data.Resource
import com.kiki.movieapp.databinding.ActivitySearchBinding
import com.kiki.movieapp.ui.detail.DetailActivity
import com.kiki.movieapp.ui.favorite.FavoriteAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivitySearchBinding.inflate(layoutInflater)
    }
    private val viewModel by viewModels<SearchViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.searchBox.apply {
            requestFocus()
            doAfterTextChanged { if (!it.isNullOrBlank()) searchMovie(it.toString()) }
        }
    }

    private fun searchMovie(query: String) {
        lifecycleScope.launch {
            viewModel.searchMovie(query)
                .flowWithLifecycle(lifecycle, Lifecycle.State.CREATED)
                .collectLatest { resource ->
                    when (resource) {
                        is Resource.Loading -> {
                            binding.loading.isVisible = true
                            binding.tvError.isVisible = false
                        }

                        is Resource.Success -> {
                            binding.loading.isVisible = false
                            binding.tvError.isVisible = false
                            val adapter = FavoriteAdapter {
                                startActivity(
                                    Intent(this@SearchActivity, DetailActivity::class.java).apply {
                                        putExtra("movie", it)
                                    }
                                )
                            }
                            binding.rvSearch.adapter = adapter
                            adapter.submitList(resource.data)
                        }

                        is Resource.Error -> {
                            binding.loading.isVisible = false
                            binding.tvError.isVisible = true
                        }
                    }
                }
        }
    }
}