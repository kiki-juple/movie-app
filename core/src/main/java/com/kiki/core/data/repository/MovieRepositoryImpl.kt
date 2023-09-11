package com.kiki.core.data.repository

import com.kiki.core.data.NetworkBoundResource
import com.kiki.core.data.Resource
import com.kiki.core.data.source.local.LocalDataSource
import com.kiki.core.data.source.remote.RemoteDataSource
import com.kiki.core.data.source.remote.ResponseState
import com.kiki.core.data.source.remote.response.MovieDetailResponse
import com.kiki.core.data.source.remote.response.MovieResponse
import com.kiki.core.data.source.remote.response.MovieResponseWithDate
import com.kiki.core.di.DispatcherModule
import com.kiki.core.domain.model.Movie
import com.kiki.core.domain.model.MovieDetail
import com.kiki.core.domain.repository.MovieRepository
import com.kiki.core.util.DataMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    @DispatcherModule.DispatcherIO private val dispatcherIO: CoroutineDispatcher
) : MovieRepository {
    override fun getPopularMovies(): Flow<Resource<List<Movie>>> {
        return object : NetworkBoundResource<List<Movie>, MovieResponse>() {
            override suspend fun saveRequestResult(data: MovieResponse) {
                return localDataSource.insertMovies(DataMapper.mapPopularMoviesToEntity(data))
            }

            override suspend fun createRequest() = remoteDataSource.getPopularMovies()

            override fun loadFromDb() = localDataSource.getPopularMovies().map {
                DataMapper.mapPopularEntityToDomain(it)
            }

            override fun shouldFetch(data: List<Movie>?) = data.isNullOrEmpty()

        }.asFlow()
    }

    override fun getNowPlayingMovies(): Flow<Resource<List<Movie>>> {
        return object : NetworkBoundResource<List<Movie>, MovieResponseWithDate>() {
            override suspend fun saveRequestResult(data: MovieResponseWithDate) {
                return localDataSource.insertMovies(DataMapper.mapNowPlayingMoviesToEntity(data))
            }

            override suspend fun createRequest() = remoteDataSource.getNowPlayingMovies()

            override fun loadFromDb() = localDataSource.getNowPlayingMovies().map {
                DataMapper.mapNowPlayingEntityToDomain(it)
            }

            override fun shouldFetch(data: List<Movie>?) = data.isNullOrEmpty()

        }.asFlow()
    }

    override fun getTopRatedMovies(): Flow<Resource<List<Movie>>> {
        return object : NetworkBoundResource<List<Movie>, MovieResponse>() {
            override suspend fun saveRequestResult(data: MovieResponse) {
                return localDataSource.insertMovies(DataMapper.mapTopRatedMoviesToEntity(data))
            }

            override suspend fun createRequest() = remoteDataSource.getTopRatedMovies()

            override fun loadFromDb() = localDataSource.getTopRatedMovies().map {
                DataMapper.mapTopRatedEntityToDomain(it)
            }

            override fun shouldFetch(data: List<Movie>?) = data.isNullOrEmpty()

        }.asFlow()
    }

    override fun getUpcomingMovies(): Flow<Resource<List<Movie>>> {
        return object : NetworkBoundResource<List<Movie>, MovieResponseWithDate>() {
            override suspend fun saveRequestResult(data: MovieResponseWithDate) {
                return localDataSource.insertMovies(DataMapper.mapUpcomingMoviesToEntity(data))
            }

            override suspend fun createRequest() = remoteDataSource.getUpcomingMovies()

            override fun loadFromDb() = localDataSource.getUpcomingMovies().map {
                DataMapper.mapNowUpcomingEntityToDomain(it)
            }

            override fun shouldFetch(data: List<Movie>?) = data.isNullOrEmpty()

        }.asFlow()
    }

    override fun getMovieDetail(id: Int): Flow<Resource<MovieDetail>> {
        return object : NetworkBoundResource<MovieDetail, MovieDetailResponse>() {
            override suspend fun saveRequestResult(data: MovieDetailResponse) {
                return localDataSource.insertMovieDetail(DataMapper.mapMovieDetailToEntity(data))
            }

            override suspend fun createRequest() = remoteDataSource.getMovieDetail(id)

            override fun loadFromDb() = localDataSource.getMovieDetail(id).map {
                DataMapper.mapMovieDetailEntityToDomain(it)
            }

            override fun shouldFetch(data: MovieDetail?) = data == MovieDetail()

        }.asFlow()
    }

    override fun searchMovie(query: String): Flow<Resource<List<Movie>>> {
        return remoteDataSource.searchMovie(query).map { response ->
            when (response) {
                is ResponseState.Success -> {
                    Resource.Success(DataMapper.mapSearchMoviesToDomain(response.data))
                }

                is ResponseState.Error -> {
                    Resource.Error(message = response.error)
                }

                is ResponseState.Empty -> Resource.Success(emptyList())
            }
        }
    }

    override fun getFavoriteMovies(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovies().map {
            DataMapper.mapFavoriteEntityToDomain(it)
        }
    }

    override fun updateFavoriteMovie(movie: Movie, isFavorite: Boolean) {
        CoroutineScope(dispatcherIO).launch {
            localDataSource.updateFavoriteMovie(
                DataMapper.mapFavoriteDomainToEntity(movie),
                isFavorite
            )
        }
    }

}