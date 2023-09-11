package com.kiki.movieapp.ui.favorite

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.kiki.movieapp.databinding.ActivityFavoriteBinding
import com.kiki.movieapp.ui.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoriteActivity : AppCompatActivity() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityFavoriteBinding.inflate(layoutInflater)
    }
    private val viewModel by viewModels<FavoriteViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.favoriteMovies.collectLatest { favoriteList ->
                    if (favoriteList.isNotEmpty()) {
                        binding.rvFavorite.isVisible = true
                        binding.tvEmpty.isVisible = false
                        val adapter = FavoriteAdapter {
                            startActivity(
                                Intent(this@FavoriteActivity, DetailActivity::class.java).apply {
                                    putExtra("movie", it)
                                }
                            )
                        }
                        binding.rvFavorite.adapter = adapter
                        adapter.submitList(favoriteList)
                    } else {
                        binding.rvFavorite.isVisible = false
                        binding.tvEmpty.isVisible = true
                    }
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}