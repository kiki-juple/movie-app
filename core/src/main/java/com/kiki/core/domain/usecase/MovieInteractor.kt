package com.kiki.core.domain.usecase

import com.kiki.core.domain.model.Movie
import com.kiki.core.domain.repository.MovieRepository
import javax.inject.Inject

class MovieInteractor @Inject constructor(
    private val movieRepository: MovieRepository
) : MovieUseCase {
    override fun getPopularMovies() = movieRepository.getPopularMovies()

    override fun getNowPlayingMovies() = movieRepository.getNowPlayingMovies()

    override fun getUpcomingMovies() = movieRepository.getUpcomingMovies()

    override fun getTopRatedMovies() = movieRepository.getTopRatedMovies()

    override fun getMovieDetail(id: Int) = movieRepository.getMovieDetail(id)

    override fun getFavoriteMovies() = movieRepository.getFavoriteMovies()

    override fun updateFavoriteMovie(movie: Movie, isFavorite: Boolean) {
        movieRepository.updateFavoriteMovie(movie, isFavorite)
    }

    override fun searchMovie(query: String) = movieRepository.searchMovie(query)


}