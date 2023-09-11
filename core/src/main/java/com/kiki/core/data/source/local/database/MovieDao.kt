package com.kiki.core.data.source.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.kiki.core.data.source.local.entity.MovieDetailEntity
import com.kiki.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie WHERE isPopular == 1")
    fun getPopularMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie WHERE isNowPlaying == 1")
    fun getNowPlayingMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie WHERE isTopRated == 1")
    fun getTopRatedMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie WHERE isUpcoming == 1")
    fun getUpcomingMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie WHERE isFavorite == 1")
    fun getFavoriteMovies(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovieDetail(movieDetailEntity: MovieDetailEntity)

    @Query("SELECT * FROM movie_detail WHERE id == :id")
    fun getMovieDetailById(id: Int): Flow<MovieDetailEntity>

    @Update
    fun updateFavoriteMovie(movie: MovieEntity)
}