package com.kiki.core.util

import com.kiki.core.data.source.local.entity.MovieDetailEntity
import com.kiki.core.data.source.local.entity.MovieEntity
import com.kiki.core.data.source.remote.response.MovieDetailResponse
import com.kiki.core.data.source.remote.response.MovieResponse
import com.kiki.core.data.source.remote.response.MovieResponseWithDate
import com.kiki.core.domain.model.Movie
import com.kiki.core.domain.model.MovieDetail

object DataMapper {

    fun mapPopularMoviesToEntity(input: MovieResponse): List<MovieEntity> {
        return input.results.map { result ->
            MovieEntity(
                id = result.id,
                title = result.title,
                overView = result.overview,
                posterUrl = result.posterPath,
                rating = result.voteAverage,
                ratingCount = result.voteCount,
                adult = result.adult,
                releaseDate = result.releaseDate,
                isPopular = true
            )
        }
    }

    fun mapPopularEntityToDomain(input: List<MovieEntity>): List<Movie> {
        return input.map { entity ->
            Movie(
                id = entity.id,
                title = entity.title,
                overView = entity.overView,
                posterUrl = entity.posterUrl,
                rating = entity.rating,
                ratingCount = entity.ratingCount,
                adult = entity.adult,
                releaseDate = entity.releaseDate,
                isPopular = entity.isPopular,
                isFavorite = entity.isFavorite
            )
        }
    }

    fun mapNowPlayingMoviesToEntity(input: MovieResponseWithDate): List<MovieEntity> {
        return input.results.map { result ->
            MovieEntity(
                id = result.id,
                title = result.title,
                overView = result.overview,
                posterUrl = result.posterPath,
                backdropUrl = result.backdropPath,
                rating = result.voteAverage,
                ratingCount = result.voteCount,
                adult = result.adult,
                releaseDate = result.releaseDate,
                isNowPlaying = true
            )
        }
    }

    fun mapNowPlayingEntityToDomain(input: List<MovieEntity>): List<Movie> {
        return input.map { entity ->
            Movie(
                id = entity.id,
                title = entity.title,
                overView = entity.overView,
                posterUrl = entity.posterUrl,
                backdropUrl = entity.backdropUrl,
                rating = entity.rating,
                ratingCount = entity.ratingCount,
                adult = entity.adult,
                releaseDate = entity.releaseDate,
                isNowPlaying = entity.isNowPlaying,
                isFavorite = entity.isFavorite
            )
        }
    }

    fun mapUpcomingMoviesToEntity(input: MovieResponseWithDate): List<MovieEntity> {
        return input.results.map { result ->
            MovieEntity(
                id = result.id,
                title = result.title,
                overView = result.overview,
                posterUrl = result.posterPath,
                rating = result.voteAverage,
                ratingCount = result.voteCount,
                adult = result.adult,
                releaseDate = result.releaseDate,
                isUpcoming = true
            )
        }
    }

    fun mapNowUpcomingEntityToDomain(input: List<MovieEntity>): List<Movie> {
        return input.map { entity ->
            Movie(
                id = entity.id,
                title = entity.title,
                overView = entity.overView,
                posterUrl = entity.posterUrl,
                rating = entity.rating,
                ratingCount = entity.ratingCount,
                adult = entity.adult,
                releaseDate = entity.releaseDate,
                isUpcoming = entity.isUpcoming,
                isFavorite = entity.isFavorite
            )
        }
    }

    fun mapTopRatedMoviesToEntity(input: MovieResponse): List<MovieEntity> {
        return input.results.map { result ->
            MovieEntity(
                id = result.id,
                title = result.title,
                overView = result.overview,
                posterUrl = result.posterPath,
                rating = result.voteAverage,
                ratingCount = result.voteCount,
                adult = result.adult,
                releaseDate = result.releaseDate,
                isTopRated = true
            )
        }
    }

    fun mapTopRatedEntityToDomain(input: List<MovieEntity>): List<Movie> {
        return input.map { entity ->
            Movie(
                id = entity.id,
                title = entity.title,
                overView = entity.overView,
                posterUrl = entity.posterUrl,
                rating = entity.rating,
                ratingCount = entity.ratingCount,
                adult = entity.adult,
                releaseDate = entity.releaseDate,
                isTopRated = entity.isTopRated,
                isFavorite = entity.isFavorite
            )
        }
    }

    fun mapMovieDetailToEntity(input: MovieDetailResponse): MovieDetailEntity {
        return MovieDetailEntity(
            id = input.id,
            backdropUrl = input.backdropPath,
            status = input.status,
            releaseDate = input.releaseDate
        )
    }

    fun mapMovieDetailEntityToDomain(input: MovieDetailEntity?): MovieDetail {
        return MovieDetail(
            id = input?.id,
            backdropUrl = input?.backdropUrl,
            status = input?.status,
            releaseDate = input?.releaseDate
        )
    }

    fun mapFavoriteEntityToDomain(input: List<MovieEntity>): List<Movie> {
        return input.map { entity ->
            Movie(
                id = entity.id,
                title = entity.title,
                overView = entity.overView,
                posterUrl = entity.posterUrl,
                backdropUrl = entity.backdropUrl,
                rating = entity.rating,
                ratingCount = entity.ratingCount,
                adult = entity.adult,
                releaseDate = entity.releaseDate,
                isPopular = entity.isPopular,
                isUpcoming = entity.isUpcoming,
                isTopRated = entity.isTopRated,
                isNowPlaying = entity.isNowPlaying,
                isFavorite = entity.isFavorite
            )
        }
    }

    fun mapFavoriteDomainToEntity(input: Movie): MovieEntity {
        return MovieEntity(
            id = input.id,
            title = input.title,
            overView = input.overView,
            posterUrl = input.posterUrl,
            backdropUrl = input.backdropUrl,
            rating = input.rating,
            ratingCount = input.ratingCount,
            adult = input.adult,
            releaseDate = input.releaseDate,
            isPopular = input.isPopular,
            isUpcoming = input.isUpcoming,
            isTopRated = input.isTopRated,
            isNowPlaying = input.isNowPlaying,
            isFavorite = input.isFavorite
        )
    }

    fun mapSearchMoviesToDomain(input: MovieResponse): List<Movie> {
        return input.results.map { result ->
            Movie(
                id = result.id,
                title = result.title,
                overView = result.overview,
                posterUrl = result.posterPath,
                rating = result.voteAverage,
                ratingCount = result.voteCount,
                adult = result.adult,
                releaseDate = result.releaseDate
            )
        }
    }
}