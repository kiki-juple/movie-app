package com.kiki.core.data.source.local

import com.kiki.core.data.source.local.database.MovieDao
import com.kiki.core.data.source.local.entity.MovieDetailEntity
import com.kiki.core.data.source.local.entity.MovieEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val movieDao: MovieDao) {

    fun getPopularMovies() = movieDao.getPopularMovies()

    fun getNowPlayingMovies() = movieDao.getNowPlayingMovies()

    fun getTopRatedMovies() = movieDao.getTopRatedMovies()

    fun getUpcomingMovies() = movieDao.getUpcomingMovies()

    fun getFavoriteMovies() = movieDao.getFavoriteMovies()

    suspend fun insertMovies(movies: List<MovieEntity>) = movieDao.insertMovies(movies)

    suspend fun insertMovieDetail(movieDetail: MovieDetailEntity) =
        movieDao.insertMovieDetail(movieDetail)

    fun getMovieDetail(id: Int) = movieDao.getMovieDetailById(id)

    fun updateFavoriteMovie(movie: MovieEntity, isFavorite: Boolean) {
        movie.isFavorite = isFavorite
        movieDao.updateFavoriteMovie(movie)
    }
}