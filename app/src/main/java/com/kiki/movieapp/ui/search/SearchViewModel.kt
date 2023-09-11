package com.kiki.movieapp.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kiki.core.data.Resource
import com.kiki.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val movieUseCase: MovieUseCase) : ViewModel() {

    fun searchMovie(query: String) = movieUseCase.searchMovie(query).stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        Resource.Loading(emptyList())
    )
}