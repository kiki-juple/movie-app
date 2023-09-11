package com.kiki.core.domain.usecase

import com.kiki.core.data.Resource
import com.kiki.core.domain.model.Movie
import com.kiki.core.domain.model.MovieDetail
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {

    fun getPopularMovies(): Flow<Resource<List<Movie>>>

    fun getNowPlayingMovies(): Flow<Resource<List<Movie>>>

    fun getUpcomingMovies(): Flow<Resource<List<Movie>>>

    fun getTopRatedMovies(): Flow<Resource<List<Movie>>>

    fun getMovieDetail(id: Int): Flow<Resource<MovieDetail>>

    fun getFavoriteMovies(): Flow<List<Movie>>

    fun updateFavoriteMovie(movie: Movie, isFavorite: Boolean)

    fun searchMovie(query: String): Flow<Resource<List<Movie>>>
}