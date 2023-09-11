package com.kiki.core.domain.repository

import com.kiki.core.data.Resource
import com.kiki.core.domain.model.Movie
import com.kiki.core.domain.model.MovieDetail
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getPopularMovies(): Flow<Resource<List<Movie>>>

    fun getNowPlayingMovies(): Flow<Resource<List<Movie>>>

    fun getTopRatedMovies(): Flow<Resource<List<Movie>>>

    fun getUpcomingMovies(): Flow<Resource<List<Movie>>>

    fun getFavoriteMovies(): Flow<List<Movie>>

    fun updateFavoriteMovie(movie: Movie, isFavorite: Boolean)

    fun getMovieDetail(id: Int): Flow<Resource<MovieDetail>>

    fun searchMovie(query: String): Flow<Resource<List<Movie>>>
}