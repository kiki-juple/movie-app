package com.kiki.core.data.source.remote.service

import com.kiki.core.data.source.remote.response.MovieDetailResponse
import com.kiki.core.data.source.remote.response.MovieResponse
import com.kiki.core.data.source.remote.response.MovieResponseWithDate
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(): MovieResponseWithDate

    @GET("movie/popular")
    suspend fun getPopularMovies(): MovieResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(): MovieResponse

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(): MovieResponseWithDate

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") id: Int
    ): MovieDetailResponse

    @GET("search/movie")
    suspend fun searchMovie(
        @Query("query") query: String
    ): MovieResponse
}