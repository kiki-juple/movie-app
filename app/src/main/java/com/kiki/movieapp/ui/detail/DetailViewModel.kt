package com.kiki.movieapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kiki.core.data.Resource
import com.kiki.core.domain.model.Movie
import com.kiki.core.domain.model.MovieDetail
import com.kiki.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val movieUseCase: MovieUseCase) : ViewModel() {

    fun getMovieDetail(id: Int): StateFlow<Resource<MovieDetail>> {
        return movieUseCase.getMovieDetail(id).stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            Resource.Loading()
        )
    }

    fun updateFavorite(movie: Movie, isFavorite: Boolean) {
        movieUseCase.updateFavoriteMovie(movie, isFavorite)
    }
}