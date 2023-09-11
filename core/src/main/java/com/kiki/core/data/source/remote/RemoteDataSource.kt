package com.kiki.core.data.source.remote

import com.kiki.core.data.source.remote.service.ApiService
import com.kiki.core.di.DispatcherModule.DispatcherIO
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val apiService: ApiService,
    @DispatcherIO private val dispatcherIO: CoroutineDispatcher
) {

    fun getNowPlayingMovies() = flow {
        try {
            val response = apiService.getNowPlayingMovies()
            if (response.totalResults != null) {
                emit(ResponseState.Success(response))
            } else {
                emit(ResponseState.Empty)
            }
        } catch (e: Exception) {
            emit(ResponseState.Error(e.localizedMessage.orEmpty()))
        }
    }.flowOn(dispatcherIO)

    fun getPopularMovies() = flow {
        try {
            val response = apiService.getPopularMovies()
            if (response.totalResults != null) {
                emit(ResponseState.Success(response))
            } else {
                emit(ResponseState.Empty)
            }
        } catch (e: Exception) {
            emit(ResponseState.Error(e.localizedMessage.orEmpty()))
        }
    }.flowOn(dispatcherIO)

    fun getTopRatedMovies() = flow {
        try {
            val response = apiService.getTopRatedMovies()
            if (response.totalResults != null) {
                emit(ResponseState.Success(response))
            } else {
                emit(ResponseState.Empty)
            }
        } catch (e: Exception) {
            emit(ResponseState.Error(e.localizedMessage.orEmpty()))
        }
    }.flowOn(dispatcherIO)

    fun getUpcomingMovies() = flow {
        try {
            val response = apiService.getUpcomingMovies()
            if (response.totalResults != null) {
                emit(ResponseState.Success(response))
            } else {
                emit(ResponseState.Empty)
            }
        } catch (e: Exception) {
            emit(ResponseState.Error(e.localizedMessage.orEmpty()))
        }
    }.flowOn(dispatcherIO)

    fun getMovieDetail(id: Int) = flow {
        try {
            val response = apiService.getMovieDetail(id)
            if (response.id != null) {
                emit(ResponseState.Success(response))
            } else {
                emit(ResponseState.Empty)
            }
        } catch (e: Exception) {
            emit(ResponseState.Error(e.localizedMessage.orEmpty()))
        }
    }.flowOn(dispatcherIO)

    fun searchMovie(query: String) = flow {
        try {
            val response = apiService.searchMovie(query)
            if (response.totalResults != null) {
                emit(ResponseState.Success(response))
            } else {
                emit(ResponseState.Empty)
            }
        } catch (e: Exception) {
            emit(ResponseState.Error(e.localizedMessage.orEmpty()))
        }
    }
}